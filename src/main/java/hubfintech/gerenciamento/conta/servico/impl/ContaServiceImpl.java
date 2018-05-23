package hubfintech.gerenciamento.conta.servico.impl;

import java.util.List;

import javax.transaction.Transactional;

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
    @Transactional
    public Conta salvarContaMatriz(Conta conta) {

        if (conta.getContaPai() != null) {
            throw new IllegalArgumentException("A conta matriz não pode ter uma conta superior");
        }
        return contaRepository.save(conta);
    }

    @Override
    public Conta consultarContaMatriz(Integer id) {
        return contaRepository.consultarMatriz(id);
    }

    @Override
    public List<Conta> listarContasMatriz() {
        return contaRepository.listarContasMatriz();
    }
}