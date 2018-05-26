package eduardopappalardo.gerenciamento.conta.servico.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eduardopappalardo.gerenciamento.conta.constante.SituacaoConta;
import eduardopappalardo.gerenciamento.conta.constante.TipoTransacao;
import eduardopappalardo.gerenciamento.conta.entidade.Conta;
import eduardopappalardo.gerenciamento.conta.entidade.Transacao;
import eduardopappalardo.gerenciamento.conta.repositorio.ContaRepository;
import eduardopappalardo.gerenciamento.conta.repositorio.TransacaoRepository;
import eduardopappalardo.gerenciamento.conta.servico.TransacaoService;
import eduardopappalardo.gerenciamento.conta.validacao.ValidacaoException;

@Service
public class TransacaoServiceImpl implements TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Transactional
	@Override
	public void efetuarTransferencia(Integer contaOrigemId, Integer contaDestinoId, BigDecimal valor) {
		Conta contaOrigem = null;
		Conta contaDestino = null;

		if (contaOrigemId == null || (contaOrigem = contaRepository.findOne(contaOrigemId)) == null) {
			throw new ValidacaoException("Conta de origem inválida");
		}
		if (contaDestinoId == null || (contaDestino = contaRepository.findOne(contaDestinoId)) == null) {
			throw new ValidacaoException("Conta de destino inválida");
		}
		this.validarTransferencia(contaOrigem, contaDestino, valor);

		contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
		contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
		contaRepository.save(contaOrigem);
		contaRepository.save(contaDestino);

		Transacao transacao = new Transacao();
		transacao.setContaOrigemDebitada(contaOrigem);
		transacao.setContaDestinoCreditada(contaDestino);
		transacao.setDataTransacao(new Date());
		transacao.setEstornada(false);
		transacao.setTipoTransacao(TipoTransacao.TRANSFERENCIA);
		transacao.setValorTransacao(valor);

		transacaoRepository.save(transacao);
	}

	@Transactional
	@Override
	public void estornarTransferencia(Integer transacaoId) {
		Transacao transacao = null;

		if (transacaoId == null || (transacao = transacaoRepository.findOne(transacaoId)) == null) {
			throw new ValidacaoException("Transação inválida");
		}
		this.validarEstornoTransferencia(transacao);

		transacao.setEstornada(true);
		transacaoRepository.save(transacao);

		Conta contaOrigem = transacao.getContaOrigemDebitada();
		Conta contaDestino = transacao.getContaDestinoCreditada();

		contaOrigem.setSaldo(contaOrigem.getSaldo().add(transacao.getValorTransacao()));
		contaDestino.setSaldo(contaDestino.getSaldo().subtract(transacao.getValorTransacao()));

		contaRepository.save(contaOrigem);
		contaRepository.save(contaDestino);
	}

	@Transactional
	@Override
	public void efetuarAporte(Integer contaDestinoId, BigDecimal valor, String codigoAporte) {
		Conta contaDestino = null;

		if (contaDestinoId == null || (contaDestino = contaRepository.findOne(contaDestinoId)) == null) {
			throw new ValidacaoException("Conta de destino inválida");
		}
		this.validarAporte(contaDestino, valor, codigoAporte);

		contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
		contaRepository.save(contaDestino);

		Transacao transacao = new Transacao();
		transacao.setContaDestinoCreditada(contaDestino);
		transacao.setDataTransacao(new Date());
		transacao.setEstornada(false);
		transacao.setTipoTransacao(TipoTransacao.APORTE);
		transacao.setValorTransacao(valor);
		transacao.setCodigoAporte(codigoAporte);

		transacaoRepository.save(transacao);
	}

	@Transactional
	@Override
	public void estornarAporte(String codigoAporte) {
		Transacao transacao = null;

		if (codigoAporte == null || (transacao = transacaoRepository.findByCodigoAporte(codigoAporte)) == null) {
			throw new ValidacaoException("Código do aporte inválido");
		}
		if (transacao.getEstornada()) {
			throw new ValidacaoException("A transacação já foi estornada");
		}
		Conta conta = transacao.getContaDestinoCreditada();
		conta.setSaldo(conta.getSaldo().subtract(transacao.getValorTransacao()));
		contaRepository.save(conta);

		transacao.setEstornada(true);
		transacaoRepository.save(transacao);
	}

	@Override
	public List<Transacao> listarTransacoes() {
		return transacaoRepository.findAll();
	}

	private void validarTransferencia(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {

		if (contaOrigem.getSituacaoConta() != SituacaoConta.ATIVA) {
			throw new ValidacaoException("A conta origem deve estar ativa");
		}
		if (contaDestino.getSituacaoConta() != SituacaoConta.ATIVA) {
			throw new ValidacaoException("A conta destino deve estar ativa");
		}
		if (!isContaDestinoFilialContaOrigem(contaOrigem, contaDestino)) {
			throw new ValidacaoException("A conta de destino deve ser filial da conta origem");
		}
		if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValidacaoException("Valor de transferência inválido");
		}
	}

	private void validarEstornoTransferencia(Transacao transacao) {

		if (transacao.getTipoTransacao() != TipoTransacao.TRANSFERENCIA) {
			throw new ValidacaoException("A tipo da transação não permite estorno de transferência");
		}
		if (transacao.getEstornada()) {
			throw new ValidacaoException("A transacação já foi estornada");
		}
	}

	private void validarAporte(Conta contaDestino, BigDecimal valor, String codigoAporte) {

		if (contaDestino.getContaPai() != null) {
			throw new ValidacaoException("Aporte não permitido para conta filial");
		}
		if (contaDestino.getSituacaoConta() != SituacaoConta.ATIVA) {
			throw new ValidacaoException("A conta deve estar ativa");
		}
		if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValidacaoException("Valor de aporte inválido");
		}
		if (codigoAporte == null || codigoAporte.trim().isEmpty()) {
			throw new ValidacaoException("Código do aporte inválido");
		}
		if (transacaoRepository.existsByCodigoAporte(codigoAporte)) {
			throw new ValidacaoException("Código do aporte já utilizado");
		}
	}

	private boolean isContaDestinoFilialContaOrigem(Conta conta, Conta contaDestino) {
		List<Conta> contasFiliais = new ArrayList<>();
		this.popularListaComContasFiliais(conta, contasFiliais);
		return contasFiliais.contains(contaDestino);
	}

	private void popularListaComContasFiliais(Conta conta, List<Conta> contasFiliais) {

		for (Conta contaFilial : conta.getContasFiliais()) {
			contasFiliais.add(contaFilial);
			this.popularListaComContasFiliais(contaFilial, contasFiliais);
		}
	}
}