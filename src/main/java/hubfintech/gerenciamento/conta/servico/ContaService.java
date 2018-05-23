package hubfintech.gerenciamento.conta.servico;

import java.util.List;

import hubfintech.gerenciamento.conta.entidade.Conta;

public interface ContaService {

    public Conta salvarContaMatriz(Conta conta);

    public Conta consultarContaMatriz(Integer id);

    public List<Conta> listarContasMatriz();

}