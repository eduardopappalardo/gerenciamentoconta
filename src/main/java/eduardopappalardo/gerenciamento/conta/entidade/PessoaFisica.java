package eduardopappalardo.gerenciamento.conta.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@PrimaryKeyJoinColumn(name = "pessoaId")
public class PessoaFisica extends Pessoa {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "CPF não pode ser vazio")
    @Column(nullable = false)
    private String cpf;

    @NotEmpty(message = "Nome completo não pode ser vazio")
    @Column(nullable = false)
    private String nomeCompleto;

    @NotNull(message = "Data de nascimento não pode ser vazia")
    @Column(nullable = false)
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PessoaFisica [cpf=");
        builder.append(cpf);
        builder.append(", nomeCompleto=");
        builder.append(nomeCompleto);
        builder.append(", dataNascimento=");
        builder.append(dataNascimento);
        builder.append("]");
        return builder.toString();
    }
}