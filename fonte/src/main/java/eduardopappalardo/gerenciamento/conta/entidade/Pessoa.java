package eduardopappalardo.gerenciamento.conta.entidade;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa extends AbstractPersistable<Integer> {

	private static final long serialVersionUID = 1L;

	@Override
	public void setId(Integer id) {
		super.setId(id);
	}
}