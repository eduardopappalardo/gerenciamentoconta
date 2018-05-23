package hubfintech.gerenciamento.conta.controller.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import hubfintech.gerenciamento.conta.entidade.Conta;

public class ContaDto {

    private Integer id;
    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataCriacao;
    private List<ContaDto> contasFiliais = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<ContaDto> getContasFiliais() {
        return contasFiliais;
    }

    public void setContasFiliais(List<ContaDto> contasFiliais) {
        this.contasFiliais = contasFiliais;
    }

    public static ContaDto converter(Conta conta) {
        ContaDto contaDto = new ContaDto();
        BeanUtils.copyProperties(conta, contaDto);
        contaDto.setId(conta.getId());
        contaDto.setContasFiliais(conta.getContasFiliais().stream().map(ContaDto::converter).collect(Collectors.toList()));
        return contaDto;
    }

    public static Conta converter(ContaDto contaDto) {
        Conta conta = new Conta();
        BeanUtils.copyProperties(contaDto, conta);
        conta.setId(conta.getId());
        contaDto.getContasFiliais().forEach(c -> conta.adicionarContaFilial(ContaDto.converter(c)));
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