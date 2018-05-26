package eduardopappalardo.gerenciamento.conta.entidade;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.AbstractPersistable;

import eduardopappalardo.gerenciamento.conta.constante.TipoTransacao;

@Entity
public class Transacao extends AbstractPersistable<Integer> {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(nullable = false)
	private Date dataTransacao;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(nullable = false)
	private TipoTransacao tipoTransacao;

	@ManyToOne
	@JoinColumn(name = "contaOrigemDebitadaId")
	private Conta contaOrigemDebitada;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "contaDestinoCreditadaId")
	private Conta contaDestinoCreditada;

	@NotNull
	@Column(nullable = false)
	private BigDecimal valorTransacao;

	@NotNull
	@Column(nullable = false)
	private Boolean estornada;

	private String codigoAporte;

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Conta getContaOrigemDebitada() {
		return contaOrigemDebitada;
	}

	public void setContaOrigemDebitada(Conta contaOrigemDebitada) {
		this.contaOrigemDebitada = contaOrigemDebitada;
	}

	public Conta getContaDestinoCreditada() {
		return contaDestinoCreditada;
	}

	public void setContaDestinoCreditada(Conta contaDestinoCreditada) {
		this.contaDestinoCreditada = contaDestinoCreditada;
	}

	public BigDecimal getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(BigDecimal valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public Boolean getEstornada() {
		return estornada;
	}

	public void setEstornada(Boolean estornada) {
		this.estornada = estornada;
	}

	public String getCodigoAporte() {
		return codigoAporte;
	}

	public void setCodigoAporte(String codigoAporte) {
		this.codigoAporte = codigoAporte;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transacao [dataTransacao=");
		builder.append(dataTransacao);
		builder.append(", tipoTransacao=");
		builder.append(tipoTransacao);
		builder.append(", contaOrigemDebitada=");
		builder.append(contaOrigemDebitada);
		builder.append(", contaDestinoCreditada=");
		builder.append(contaDestinoCreditada);
		builder.append(", valorTransacao=");
		builder.append(valorTransacao);
		builder.append(", estornada=");
		builder.append(estornada);
		builder.append(", codigoAporte=");
		builder.append(codigoAporte);
		builder.append("]");
		return builder.toString();
	}
}