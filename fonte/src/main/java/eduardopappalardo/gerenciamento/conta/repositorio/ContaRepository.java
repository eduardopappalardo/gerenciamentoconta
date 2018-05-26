package eduardopappalardo.gerenciamento.conta.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eduardopappalardo.gerenciamento.conta.entidade.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

	public List<Conta> findByContaPaiIsNull();

}