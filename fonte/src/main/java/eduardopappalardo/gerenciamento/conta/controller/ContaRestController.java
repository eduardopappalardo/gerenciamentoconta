package eduardopappalardo.gerenciamento.conta.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eduardopappalardo.gerenciamento.conta.controller.dto.ContaDto;
import eduardopappalardo.gerenciamento.conta.entidade.Conta;
import eduardopappalardo.gerenciamento.conta.servico.ContaService;
import eduardopappalardo.gerenciamento.conta.validacao.ValidacaoException;

@RestController
@RequestMapping(value = "/conta")
public class ContaRestController {

	@Autowired
	private ContaService contaService;

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody ContaDto contaDto) {
		try {
			Conta conta = contaService.salvar(contaDto.converterParaModelo());
			return ResponseEntity.ok(ContaDto.converterParaDto(conta));

		} catch (ValidacaoException e) {
			return ResponseEntity.badRequest().body(e.getMensagens());
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<ContaDto> consultarPorId(@PathVariable("id") Integer id) {
		Conta conta = contaService.consultarPorId(id);

		if (conta == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ContaDto.converterParaDto(contaService.consultarPorId(id)));
	}

	@GetMapping
	public ResponseEntity<List<ContaDto>> listarContasMatriz() {
		return ResponseEntity.ok(contaService.listarContasMatriz().stream().map(ContaDto::converterParaDto)
				.collect(Collectors.toList()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> tratarExcecao(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}