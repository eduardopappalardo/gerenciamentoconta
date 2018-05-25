package eduardopappalardo.gerenciamento.conta.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eduardopappalardo.gerenciamento.conta.entidade.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

    @Query("FROM Conta c WHERE (c.id = ?1 AND c.contaPai IS NULL) OR c.contaMatriz.id = ?1")
    public List<Conta> consultarContaMatrizEFiliais(Integer id);

    @Query("FROM Conta c WHERE c.contaPai IS NULL")
    public List<Conta> listarContasMatriz();

}