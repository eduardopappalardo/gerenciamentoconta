package eduardopappalardo.gerenciamento.conta.validacao;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validador {

	@Autowired
	private Validator validator;

	public void validar(Object object, Class<?>... groups) {
		List<String> mensagens = validarComRetorno(object, groups);

		if (!mensagens.isEmpty()) {
			throw new ValidacaoException(mensagens);
		}
	}

	public <T> List<String> validarComRetorno(T object, Class<?>... groups) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
		return constraintViolations.stream().map(c -> c.getMessage()).collect(Collectors.toList());
	}
}