package eduardopappalardo.gerenciamento.conta.servico;

import java.util.List;

import eduardopappalardo.gerenciamento.conta.entidade.Conta;

public interface ContaService {

	public Conta salvar(Conta conta);

	public Conta consultarPorId(Integer id);

	public List<Conta> listarContasMatriz();

}