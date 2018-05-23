package hubfintech.gerenciamento.conta.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hubfintech.gerenciamento.conta.entidade.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

    @Query("FROM Conta c WHERE c.contaPai IS NULL")
    public List<Conta> listarContasMatriz();

    @Query("FROM Conta c WHERE c.id = ?1 AND c.contaPai IS NULL")
    public Conta consultarMatriz(Integer id);

}