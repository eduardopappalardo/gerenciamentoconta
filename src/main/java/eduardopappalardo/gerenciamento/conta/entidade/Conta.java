package eduardopappalardo.gerenciamento.conta.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import eduardopappalardo.gerenciamento.conta.constante.SituacaoConta;

@Entity
public class Conta extends AbstractPersistable<Integer> {

	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoaId")
	@Valid
	private Pessoa pessoa;

	@NotEmpty(message = "Nome da conta não pode ser vazio")
	@Column(nullable = false)
	private String nome;

	@NotNull
	@Column(nullable = false)
	private Date dataCriacao;

	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SituacaoConta situacaoConta;

	@ManyToOne
	@JoinColumn(name = "contaMatrizId")
	private Conta contaMatriz;

	@ManyToOne
	@JoinColumn(name = "contaPaiId")
	private Conta contaPai;

	@OneToMany(mappedBy = "contaPai", cascade = CascadeType.ALL)
	private List<Conta> contasFiliais = new ArrayList<>();

	public void setId(Integer id) {
		super.setId(id);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

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

	public SituacaoConta getSituacaoConta() {
		return situacaoConta;
	}

	public void setSituacaoConta(SituacaoConta situacaoConta) {
		this.situacaoConta = situacaoConta;
	}

	public Conta getContaMatriz() {
		return contaMatriz;
	}

	public void setContaMatriz(Conta contaMatriz) {
		this.contaMatriz = contaMatriz;
	}

	public Conta getContaPai() {
		return contaPai;
	}

	public void setContaPai(Conta contaPai) {
		this.contaPai = contaPai;
	}

	public List<Conta> getContasFiliais() {
		return contasFiliais;
	}

	public void adicionarContaFilial(Conta contaFilial) {
		contaFilial.setContaPai(this);
		this.contasFiliais.add(contaFilial);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Conta [nome=");
		builder.append(nome);
		builder.append(", dataCriacao=");
		builder.append(dataCriacao);
		builder.append(", contaPai=");
		builder.append(contaPai);
		builder.append("]");
		return builder.toString();
	}
}