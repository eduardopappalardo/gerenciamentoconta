package eduardopappalardo.gerenciamento.conta.controller.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import eduardopappalardo.gerenciamento.conta.constante.SituacaoConta;
import eduardopappalardo.gerenciamento.conta.entidade.Conta;

public class ContaDto {

	private Integer id;
	private PessoaDto pessoa;
	private String nome;
	public BigDecimal saldo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataCriacao;
	private SituacaoConta situacaoConta;
	private List<ContaDto> contasFiliais = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PessoaDto getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDto pessoa) {
		this.pessoa = pessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
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

	public List<ContaDto> getContasFiliais() {
		return contasFiliais;
	}

	public void setContasFiliais(List<ContaDto> contasFiliais) {
		this.contasFiliais = contasFiliais;
	}

	public static ContaDto converterParaDto(Conta conta) {
		ContaDto contaDto = new ContaDto();
		BeanUtils.copyProperties(conta, contaDto);
		contaDto.setId(conta.getId());

		if (conta.getPessoa() != null) {
			contaDto.setPessoa(PessoaDto.converterParaDto(conta.getPessoa()));
		}
		contaDto.setContasFiliais(
				conta.getContasFiliais().stream().map(ContaDto::converterParaDto).collect(Collectors.toList()));
		return contaDto;
	}

	public Conta converterParaModelo() {
		Conta conta = new Conta();
		BeanUtils.copyProperties(this, conta);
		conta.setId(conta.getId());

		if (this.getPessoa() != null) {
			conta.setPessoa(this.getPessoa().converterParaModelo());
		}
		this.getContasFiliais().forEach(c -> conta.adicionarContaFilial(c.converterParaModelo()));
		return conta;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContaDto [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", dataCriacao=");
		builder.append(dataCriacao);
		builder.append(", contasFiliais=");
		builder.append(contasFiliais);
		builder.append("]");
		return builder.toString();
	}
}