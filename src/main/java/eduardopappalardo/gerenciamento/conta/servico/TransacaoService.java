package eduardopappalardo.gerenciamento.conta.servico;

import java.math.BigDecimal;

public interface TransacaoService {

    public void transferirValor(Integer contaOrigemId, Integer contaDestinoId, BigDecimal valor);

    public void efetuarAporte(Integer contaMatrizId, BigDecimal valor, String codigoAporte);

}