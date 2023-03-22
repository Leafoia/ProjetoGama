package br.gama.itau.projetogama04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gama.itau.projetogama04.dto.MovimentacaoDTO;
import br.gama.itau.projetogama04.model.Movimentacao;
import br.gama.itau.projetogama04.service.ContaService;
import br.gama.itau.projetogama04.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    
    @Autowired
    public MovimentacaoService movimentacaoService;
    @Autowired
    public ContaService contaService;

    @PostMapping
    public ResponseEntity<Movimentacao> newMovimentacao(@RequestBody Movimentacao novaMovimentacao) {
        Movimentacao movimentacaoInserida = movimentacaoService.newMovimentacao(novaMovimentacao);

        if(movimentacaoInserida == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoInserida);
    }

    @GetMapping("/{numeroConta}")
    public ResponseEntity<List<MovimentacaoDTO>> getMovId(@PathVariable Long numeroConta) {
        List<MovimentacaoDTO> listaMovimentacao = movimentacaoService.getMovId(numeroConta);

        if(listaMovimentacao == null || listaMovimentacao.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaMovimentacao);
    }

    // @PutMapping("/transferencia/{contaOrigem}/{contaDestino}/{valor}")
    // public ResponseEntity<Boolean> putValores(@PathVariable long contaOrigem,@PathVariable long contaDestino,@PathVariable double valor) {
    //     boolean valoresInseridos = movimentacaoService.transferirValores(contaOrigem, contaDestino, valor);
     
    //     if(valoresInseridos == false) {
    //         return ResponseEntity.badRequest().build(); 
    //     }

    //     return ResponseEntity.ok(valoresInseridos);
    // }             FALTA OS TESTE

}
