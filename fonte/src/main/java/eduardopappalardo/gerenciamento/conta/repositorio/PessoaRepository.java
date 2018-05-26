package eduardopappalardo.gerenciamento.conta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import eduardopappalardo.gerenciamento.conta.entidade.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}