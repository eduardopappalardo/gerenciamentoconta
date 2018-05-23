package hubfintech.gerenciamento.conta;

import javax.annotation.PostConstruct;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import hubfintech.gerenciamento.conta.servico.ContaService;

/**
 * Usado durante desenvolvimento
 */
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
	}
}