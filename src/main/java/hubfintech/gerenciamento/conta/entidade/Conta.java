package hubfintech.gerenciamento.conta.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Conta extends AbstractPersistable<Integer> {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private Date dataCriacao = new Date();

    @ManyToOne
    @JoinColumn(name = "contaPaiId")
    private Conta contaPai;

    @OneToMany(mappedBy = "contaPai", cascade = CascadeType.ALL)
    private List<Conta> contasFiliais = new ArrayList<>();

    public void setId(Integer id) {
        super.setId(id);
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