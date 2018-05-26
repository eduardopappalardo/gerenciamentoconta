package eduardopappalardo.gerenciamento.conta.controller.dto;

import org.springframework.beans.BeanUtils;

import eduardopappalardo.gerenciamento.conta.entidade.PessoaJuridica;

public class PessoaJuridicaDto extends PessoaDto {

    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public static PessoaJuridicaDto converterParaDto(PessoaJuridica pessoaJuridica) {
        PessoaJuridicaDto pessoaJuridicaDto = new PessoaJuridicaDto();
        BeanUtils.copyProperties(pessoaJuridica, pessoaJuridicaDto);
        pessoaJuridicaDto.setId(pessoaJuridica.getId());
        return pessoaJuridicaDto;
    }

    @Override
    public PessoaJuridica converterParaModelo() {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        BeanUtils.copyProperties(this, pessoaJuridica);
        pessoaJuridica.setId(this.getId());
        return pessoaJuridica;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PessoaJuridicaDto [cnpj=");
        builder.append(cnpj);
        builder.append(", razaoSocial=");
        builder.append(razaoSocial);
        builder.append(", nomeFantasia=");
        builder.append(nomeFantasia);
        builder.append("]");
        return builder.toString();
    }
}