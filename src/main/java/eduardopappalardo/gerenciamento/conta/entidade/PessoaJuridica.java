package eduardopappalardo.gerenciamento.conta.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@PrimaryKeyJoinColumn(name = "pessoaId")
public class PessoaJuridica extends Pessoa {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "CNPJ não pode ser vazio")
    @Column(nullable = false)
    private String cnpj;

    @NotEmpty(message = "Razão social não pode ser vazia")
    @Column(nullable = false)
    private String razaoSocial;

    @NotEmpty(message = "Nome fantasia não pode ser vazio")
    @Column(nullable = false)
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PessoaJuridica [cnpj=");
        builder.append(cnpj);
        builder.append(", razaoSocial=");
        builder.append(razaoSocial);
        builder.append(", nomeFantasia=");
        builder.append(nomeFantasia);
        builder.append("]");
        return builder.toString();
    }
}