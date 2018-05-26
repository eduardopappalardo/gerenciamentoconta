package eduardopappalardo.gerenciamento.conta;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import eduardopappalardo.gerenciamento.conta.entidade.Conta;
import eduardopappalardo.gerenciamento.conta.entidade.PessoaFisica;
import eduardopappalardo.gerenciamento.conta.entidade.PessoaJuridica;
import eduardopappalardo.gerenciamento.conta.servico.ContaService;
import eduardopappalardo.gerenciamento.conta.servico.PessoaService;

@Component
public class MassaDeDados {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Autowired
    private ContaService contaService;

    @Autowired
    private PessoaService pessoaService;

    @PostConstruct
    public void iniciar() {
        gerarMassaDados();
        abrirVisualizadorBanco();
    }

    private void abrirVisualizadorBanco() {
        System.setProperty("java.awt.headless", "false");
        DatabaseManagerSwing.main(new String[] { "--url", dataSourceUrl, "--user", "sa", "--password", "" });
    }

    private void gerarMassaDados() {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj("76.374.429/0001-00");
        pessoaJuridica.setNomeFantasia("Eduardo Tecnologia");
        pessoaJuridica.setRazaoSocial("Eduardo Corp");
        pessoaService.salvar(pessoaJuridica);

        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setCpf("123.456.789-10");
        pessoaFisica.setNomeCompleto("Eduardo");
        pessoaFisica.setDataNascimento(new Date());
        pessoaService.salvar(pessoaFisica);

        Conta contaMatriz = new Conta();
        contaMatriz.setNome("Conta Matriz 1");
        contaMatriz.setPessoa(pessoaJuridica);

        Conta contaFilial11 = new Conta();
        contaFilial11.setNome("Conta Filial 1.1");
        contaFilial11.setPessoa(pessoaFisica);

        Conta contaFilial12 = new Conta();
        contaFilial12.setNome("Conta Filial 1.2");
        contaFilial12.setPessoa(pessoaFisica);

        Conta contaFilial111 = new Conta();
        contaFilial111.setNome("Conta Filial 1.1.1");
        contaFilial111.setPessoa(pessoaFisica);

        Conta contaFilial121 = new Conta();
        contaFilial121.setNome("Conta Filial 1.2.1");
        contaFilial121.setPessoa(pessoaFisica);

        Conta contaFilial122 = new Conta();
        contaFilial122.setNome("Conta Filial 1.2.2");
        contaFilial122.setPessoa(pessoaFisica);

        contaMatriz.adicionarContaFilial(contaFilial11);
        contaMatriz.adicionarContaFilial(contaFilial12);
        contaFilial11.adicionarContaFilial(contaFilial111);
        contaFilial12.adicionarContaFilial(contaFilial121);
        contaFilial12.adicionarContaFilial(contaFilial122);

        contaService.salvar(contaMatriz);
    }
}