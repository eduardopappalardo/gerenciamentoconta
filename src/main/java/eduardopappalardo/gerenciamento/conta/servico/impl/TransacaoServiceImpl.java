package eduardopappalardo.gerenciamento.conta.servico.impl;

import java.math.BigDecimal;
import java.util.List;

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
    public void transferirValor(Integer contaOrigemId, Integer contaDestinoId, BigDecimal valor) {
        Conta contaOrigem = contaService.consultarConta(contaOrigemId);
        Conta contaDestino = contaService.consultarConta(contaDestinoId);
        boolean isContaDestinoFilialContaOrigem = isContaDestinoFilialContaOrigem(contaOrigem, contaDestino);
    }

    @Override
    public void efetuarAporte(Integer contaMatrizId, BigDecimal valor, String codigoAporte) {
    }

    private boolean isContaDestinoFilialContaOrigem(Conta conta, Conta contaDestino) {
        List<Conta> contasFiliais = conta.getContasFiliais();

        for (Conta contaFilial : contasFiliais) {
        }
        return false;
    }
}