package eduardopappalardo.gerenciamento.conta.servico;

import java.math.BigDecimal;
import java.util.List;

import eduardopappalardo.gerenciamento.conta.entidade.Transacao;

public interface TransacaoService {

	public void efetuarTransferencia(Integer contaOrigemId, Integer contaDestinoId, BigDecimal valor);

	public void estornarTransferencia(Integer transacaoId);

	public void efetuarAporte(Integer contaDestinoId, BigDecimal valor, String codigoAporte);

	public void estornarAporte(String codigoAporte);

	public List<Transacao> listarTransacoes();

}