package eduardopappalardo.gerenciamento.conta.controller.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import eduardopappalardo.gerenciamento.conta.entidade.Pessoa;
import eduardopappalardo.gerenciamento.conta.entidade.PessoaFisica;

public class PessoaFisicaDto extends PessoaDto {

    private String cpf;
    private String nomeCompleto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public static PessoaFisicaDto converterParaDto(PessoaFisica pessoaFisica) {
        PessoaFisicaDto pessoaFisicaDto = new PessoaFisicaDto();
        BeanUtils.copyProperties(pessoaFisica, pessoaFisicaDto);
        pessoaFisicaDto.setId(pessoaFisica.getId());
        return pessoaFisicaDto;
    }

    @Override
    public Pessoa converterParaModelo() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        BeanUtils.copyProperties(this, pessoaFisica);
        pessoaFisica.setId(this.getId());
        return pessoaFisica;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PessoaFisicaDto [cpf=");
        builder.append(cpf);
        builder.append(", nomeCompleto=");
        builder.append(nomeCompleto);
        builder.append(", dataNascimento=");
        builder.append(dataNascimento);
        builder.append("]");
        return builder.toString();
    }
}