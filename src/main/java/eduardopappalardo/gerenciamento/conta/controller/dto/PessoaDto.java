package eduardopappalardo.gerenciamento.conta.controller.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import eduardopappalardo.gerenciamento.conta.entidade.Pessoa;
import eduardopappalardo.gerenciamento.conta.entidade.PessoaFisica;
import eduardopappalardo.gerenciamento.conta.entidade.PessoaJuridica;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({ @Type(value = PessoaFisicaDto.class, name = "PF"),
		@Type(value = PessoaJuridicaDto.class, name = "PJ") })
public abstract class PessoaDto {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static PessoaDto converterParaDto(Pessoa pessoa) {
		return pessoa instanceof PessoaFisica ? PessoaFisicaDto.converterParaDto((PessoaFisica) pessoa)
				: PessoaJuridicaDto.converterParaDto((PessoaJuridica) pessoa);
	}

	public Pessoa converterParaModelo() {
		return this instanceof PessoaFisicaDto ? ((PessoaFisicaDto) this).converterParaModelo()
				: ((PessoaJuridicaDto) this).converterParaModelo();
	}
}