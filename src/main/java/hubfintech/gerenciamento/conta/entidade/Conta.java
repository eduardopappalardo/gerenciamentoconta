package hubfintech.gerenciamento.conta.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Conta extends AbstractPersistable<Integer> {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String nome;

	@NotEmpty
	private Date dataCriacao;

	// @ManyToOne
	// @JoinColumn(name = "contaMatrizId")
	// private Conta contaMatriz;

	@ManyToOne
	@JoinColumn(name = "contaPaiId")
	private Conta contaPai;

	@OneToMany(mappedBy = "contaPai")
	private List<Conta> contasFiliais;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	// public Conta getContaMatriz() {
	// return contaMatriz;
	// }

	// public void setContaMatriz(Conta contaMatriz) {
	// this.contaMatriz = contaMatriz;
	// }

	public Conta getContaPai() {
		return contaPai;
	}

	public void setContaPai(Conta contaPai) {
		this.contaPai = contaPai;
	}

	public List<Conta> getContasFiliais() {
		return contasFiliais;
	}

	public void setContasFiliais(List<Conta> contasFiliais) {
		this.contasFiliais = contasFiliais;
	}
}