package hubfintech.gerenciamento.conta;

import javax.annotation.PostConstruct;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import hubfintech.gerenciamento.conta.entidade.Conta;
import hubfintech.gerenciamento.conta.servico.ContaService;

@Component
public class MassaDeDados {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Autowired
    private ContaService contaService;

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
        Conta contaMatriz = new Conta();
        contaMatriz.setNome("Conta Matriz 1");

        Conta contaFilial11 = new Conta();
        contaFilial11.setNome("Conta Filial 1.1");

        Conta contaFilial12 = new Conta();
        contaFilial12.setNome("Conta Filial 1.2");

        contaMatriz.adicionarContaFilial(contaFilial11);
        contaMatriz.adicionarContaFilial(contaFilial12);

        Conta contaFilial111 = new Conta();
        contaFilial111.setNome("Conta Filial 1.1.1");

        contaFilial11.adicionarContaFilial(contaFilial111);

        Conta contaFilial121 = new Conta();
        contaFilial121.setNome("Conta Filial 1.2.1");

        Conta contaFilial122 = new Conta();
        contaFilial122.setNome("Conta Filial 1.2.2");

        contaFilial12.adicionarContaFilial(contaFilial121);
        contaFilial12.adicionarContaFilial(contaFilial122);

        contaService.salvarContaMatriz(contaMatriz);
    }
}