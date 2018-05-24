package eduardopappalardo.gerenciamento.conta.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eduardopappalardo.gerenciamento.conta.entidade.Pessoa;
import eduardopappalardo.gerenciamento.conta.repositorio.PessoaRepository;
import eduardopappalardo.gerenciamento.conta.servico.PessoaService;
import eduardopappalardo.gerenciamento.conta.validacao.Validador;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private Validador validador;

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        validador.validar(pessoa);
        return pessoaRepository.save(pessoa);
    }
}