package eduardopappalardo.gerenciamento.conta.servico.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eduardopappalardo.gerenciamento.conta.constante.SituacaoConta;
import eduardopappalardo.gerenciamento.conta.entidade.Conta;
import eduardopappalardo.gerenciamento.conta.repositorio.ContaRepository;
import eduardopappalardo.gerenciamento.conta.servico.ContaService;
import eduardopappalardo.gerenciamento.conta.validacao.Validador;

@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private Validador validador;

	@Override
	@Transactional
	public Conta salvar(Conta conta) {
		this.popularConta(conta);
		this.navegarContasFiliais(conta);
		validador.validar(conta);
		return contaRepository.save(conta);
	}

	@Override
	public Conta consultarPorId(Integer id) {
		return contaRepository.findOne(id);
	}

	@Override
	public List<Conta> listarContasMatriz() {
		return contaRepository.findByContaPaiIsNull();
	}

	private void navegarContasFiliais(Conta conta) {
		for (Conta contaFilial : conta.getContasFiliais()) {
			this.popularConta(contaFilial);
			this.navegarContasFiliais(contaFilial);
		}
	}

	private void popularConta(Conta conta) {
		if (conta.getId() == null) {
			conta.setDataCriacao(new Date());
			conta.setSituacaoConta(SituacaoConta.ATIVA);
			conta.setSaldo(BigDecimal.ZERO);
		} else {
			Conta contaDb = contaRepository.findOne(conta.getId());
			conta.setDataCriacao(contaDb.getDataCriacao());
			conta.setSaldo(contaDb.getSaldo());
		}
	}
}