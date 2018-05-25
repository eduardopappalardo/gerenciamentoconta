package eduardopappalardo.gerenciamento.conta.servico;

import java.math.BigDecimal;

import eduardopappalardo.gerenciamento.conta.entidade.Conta;

public interface TransacaoService {

    public void transferirValor(Conta contaOrigem, Conta contaDestino, BigDecimal valor);

    public void efetuarAporte(Conta contaMatriz, BigDecimal valor, String codigoAporte);

}