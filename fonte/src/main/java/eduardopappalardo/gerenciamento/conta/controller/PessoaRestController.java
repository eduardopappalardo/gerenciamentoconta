package eduardopappalardo.gerenciamento.conta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eduardopappalardo.gerenciamento.conta.controller.dto.PessoaDto;
import eduardopappalardo.gerenciamento.conta.entidade.Pessoa;
import eduardopappalardo.gerenciamento.conta.servico.PessoaService;
import eduardopappalardo.gerenciamento.conta.validacao.ValidacaoException;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaRestController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody PessoaDto pessoaDto) {
		try {
			Pessoa pessoa = pessoaService.salvar(pessoaDto.converterParaModelo());
			return ResponseEntity.ok(PessoaDto.converterParaDto(pessoa));

		} catch (ValidacaoException e) {
			return ResponseEntity.badRequest().body(e.getMensagens());
		}
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> tratarExcecao(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}