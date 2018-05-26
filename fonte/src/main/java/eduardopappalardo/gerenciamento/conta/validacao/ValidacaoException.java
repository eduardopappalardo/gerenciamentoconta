package eduardopappalardo.gerenciamento.conta.validacao;

import java.util.Arrays;
import java.util.List;

public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<String> mensagens;

	public ValidacaoException(List<String> mensagens) {
		super(String.join("\n", mensagens));
		this.mensagens = mensagens;
	}

	public ValidacaoException(String mensagem) {
		this(Arrays.asList(mensagem));
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public static void lancarExcecaoSeHouverErros(List<String> mensagens) {
		if (!mensagens.isEmpty()) {
			throw new ValidacaoException(mensagens);
		}
	}
}