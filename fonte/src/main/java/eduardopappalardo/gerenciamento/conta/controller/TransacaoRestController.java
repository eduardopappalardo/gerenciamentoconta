package eduardopappalardo.gerenciamento.conta.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eduardopappalardo.gerenciamento.conta.controller.dto.TransacaoDto;
import eduardopappalardo.gerenciamento.conta.servico.TransacaoService;
import eduardopappalardo.gerenciamento.conta.validacao.ValidacaoException;

@RestController
@RequestMapping(value = "/transacao")
public class TransacaoRestController {

	@Autowired
	private TransacaoService transacaoService;

	@PostMapping("/efetuar-transferencia")
	public ResponseEntity<?> efetuarTransferencia(@RequestBody TransacaoDto transacaoDto) {
		try {
			transacaoService.efetuarTransferencia(transacaoDto.getContaOrigemId(), transacaoDto.getContaDestinoId(),
					transacaoDto.getValorTransacao());
			return ResponseEntity.ok().build();

		} catch (ValidacaoException e) {
			return ResponseEntity.badRequest().body(e.getMensagens());
		}
	}

	@PostMapping("/estornar-transferencia")
	public ResponseEntity<?> estornarTransferencia(@RequestBody TransacaoDto transacaoDto) {
		try {
			transacaoService.estornarTransferencia(transacaoDto.getTransacaoId());
			return ResponseEntity.ok().build();

		} catch (ValidacaoException e) {
			return ResponseEntity.badRequest().body(e.getMensagens());
		}
	}

	@PostMapping("/efetuar-aporte")
	public ResponseEntity<?> efetuarAporte(@RequestBody TransacaoDto transacaoDto) {
		try {
			transacaoService.efetuarAporte(transacaoDto.getContaDestinoId(), transacaoDto.getValorTransacao(),
					transacaoDto.getCodigoAporte());
			return ResponseEntity.ok().build();

		} catch (ValidacaoException e) {
			return ResponseEntity.badRequest().body(e.getMensagens());
		}
	}

	@PostMapping("/estornar-aporte")
	public ResponseEntity<?> estornarAporte(@RequestBody TransacaoDto transacaoDto) {
		try {
			transacaoService.estornarAporte(transacaoDto.getCodigoAporte());
			return ResponseEntity.ok().build();

		} catch (ValidacaoException e) {
			return ResponseEntity.badRequest().body(e.getMensagens());
		}
	}

	@GetMapping
	public ResponseEntity<List<TransacaoDto>> listarTransacoes() {
		return ResponseEntity.ok(transacaoService.listarTransacoes().stream().map(TransacaoDto::converterParaDto)
				.collect(Collectors.toList()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> tratarExcecao(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}