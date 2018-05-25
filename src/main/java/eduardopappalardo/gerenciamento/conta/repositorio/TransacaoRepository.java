package eduardopappalardo.gerenciamento.conta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import eduardopappalardo.gerenciamento.conta.entidade.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
}