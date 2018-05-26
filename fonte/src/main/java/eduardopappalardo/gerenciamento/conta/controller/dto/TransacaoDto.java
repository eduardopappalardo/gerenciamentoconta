package eduardopappalardo.gerenciamento.conta.controller.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import eduardopappalardo.gerenciamento.conta.constante.TipoTransacao;
import eduardopappalardo.gerenciamento.conta.entidade.Conta;
import eduardopappalardo.gerenciamento.conta.entidade.Transacao;

public class TransacaoDto {

	private Integer transacaoId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataTransacao;
	private TipoTransacao tipoTransacao;
	private Integer contaOrigemId;
	private Integer contaDestinoId;
	private BigDecimal valorTransacao;
	private Boolean estornada;
	private String codigoAporte;

	public Integer getTransacaoId() {
		return transacaoId;
	}

	public void setTransacaoId(Integer transacaoId) {
		this.transacaoId = transacaoId;
	}

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

	public Integer getContaOrigemId() {
		return contaOrigemId;
	}

	public void setContaOrigemId(Integer contaOrigemId) {
		this.contaOrigemId = contaOrigemId;
	}

	public Integer getContaDestinoId() {
		return contaDestinoId;
	}

	public void setContaDestinoId(Integer contaDestinoId) {
		this.contaDestinoId = contaDestinoId;
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

	public static TransacaoDto converterParaDto(Transacao transacao) {
		TransacaoDto transacaoDto = new TransacaoDto();
		BeanUtils.copyProperties(transacao, transacaoDto);
		transacaoDto.setTransacaoId(transacao.getId());
		transacaoDto
				.setContaOrigemId(Optional.ofNullable(transacao.getContaOrigemDebitada()).orElse(new Conta()).getId());
		transacaoDto.setContaDestinoId(
				Optional.ofNullable(transacao.getContaDestinoCreditada()).orElse(new Conta()).getId());
		return transacaoDto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransacaoDto [transacaoId=");
		builder.append(transacaoId);
		builder.append(", dataTransacao=");
		builder.append(dataTransacao);
		builder.append(", tipoTransacao=");
		builder.append(tipoTransacao);
		builder.append(", contaOrigemId=");
		builder.append(contaOrigemId);
		builder.append(", contaDestinoId=");
		builder.append(contaDestinoId);
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