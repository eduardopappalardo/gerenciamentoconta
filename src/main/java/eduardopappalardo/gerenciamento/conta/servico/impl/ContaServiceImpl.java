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
import eduardopappalardo.gerenciamento.conta.validacao.ValidacaoException;
import eduardopappalardo.gerenciamento.conta.validacao.Validador;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private Validador validador;

    @Override
    @Transactional
    public Conta salvarContaMatriz(Conta conta) {
        this.popularNovaConta(conta);
        this.navegarContasFiliais(conta, conta);

        validador.validar(conta);

        if (conta.getContaPai() != null) {
            throw new ValidacaoException("A conta matriz não pode ter uma conta pai");
        }
        return contaRepository.save(conta);
    }

    @Override
    public Conta consultarContaMatriz(Integer id) {
        return contaRepository.consultarContaMatrizEFiliais(id).stream().filter(c -> c.getContaPai() == null)
                .findFirst().get();
    }

    @Override
    public List<Conta> listarContasMatriz() {
        return contaRepository.listarContasMatriz();
    }

    @Override
    public Conta consultarConta(Integer id) {
        return contaRepository.findOne(id);
    }

    private void navegarContasFiliais(Conta conta, Conta contaMatriz) {
        for (Conta contaFilial : conta.getContasFiliais()) {
            this.popularNovaConta(contaFilial);
            this.navegarContasFiliais(contaFilial, contaMatriz);
            contaFilial.setContaMatriz(contaMatriz);
        }
    }

    private void popularNovaConta(Conta conta) {
        if (conta.getId() == null) {
            conta.setDataCriacao(new Date());
            conta.setSituacaoConta(SituacaoConta.ATIVA);
            conta.setSaldo(BigDecimal.ZERO);
        }
    }
}