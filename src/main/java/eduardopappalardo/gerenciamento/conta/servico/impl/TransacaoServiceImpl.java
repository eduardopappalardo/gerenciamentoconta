package eduardopappalardo.gerenciamento.conta.servico.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eduardopappalardo.gerenciamento.conta.entidade.Conta;
import eduardopappalardo.gerenciamento.conta.repositorio.TransacaoRepository;
import eduardopappalardo.gerenciamento.conta.servico.ContaService;
import eduardopappalardo.gerenciamento.conta.servico.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaService contaService;

    @Override
    public void transferirValor(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
        // TODO Auto-generated method stub

    }

    @Override
    public void efetuarAporte(Conta contaMatriz, BigDecimal valor, String codigoAporte) {
        // TODO Auto-generated method stub

    }
}