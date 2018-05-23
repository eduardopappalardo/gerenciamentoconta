package hubfintech.gerenciamento.conta.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hubfintech.gerenciamento.conta.entidade.Conta;
import hubfintech.gerenciamento.conta.repositorio.ContaRepository;
import hubfintech.gerenciamento.conta.servico.ContaService;

@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Override
	public Conta salvarContaMatriz(Conta conta) {
		return contaRepository.save(conta);
	}
}