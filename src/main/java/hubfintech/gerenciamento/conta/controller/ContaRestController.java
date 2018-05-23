package hubfintech.gerenciamento.conta.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hubfintech.gerenciamento.conta.controller.dto.ContaDto;
import hubfintech.gerenciamento.conta.entidade.Conta;
import hubfintech.gerenciamento.conta.servico.ContaService;

@RestController
@RequestMapping(value = "/conta")
public class ContaRestController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaDto> salvarContaMatriz(@RequestBody ContaDto contaDto) {
        Conta conta = contaService.salvarContaMatriz(ContaDto.converter(contaDto));
        return ResponseEntity.ok(ContaDto.converter(conta));
    }

    @GetMapping("{id}")
    public ResponseEntity<ContaDto> consultarContaMatriz(@PathVariable("id") Integer id) {
        Conta contaMatriz = contaService.consultarContaMatriz(id);

        if (contaMatriz == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ContaDto.converter(contaService.consultarContaMatriz(id)));
    }

    @GetMapping
    public ResponseEntity<List<ContaDto>> listarContasMatriz() {
        return ResponseEntity.ok(contaService.listarContasMatriz().stream().map(ContaDto::converter).collect(Collectors.toList()));
    }

}