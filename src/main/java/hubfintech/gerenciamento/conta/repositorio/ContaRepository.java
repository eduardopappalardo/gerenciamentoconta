package hubfintech.gerenciamento.conta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import hubfintech.gerenciamento.conta.entidade.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
}