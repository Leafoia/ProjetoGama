package br.gama.itau.projetogama04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gama.itau.projetogama04.model.Movimentacao;
import br.gama.itau.projetogama04.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    
    @Autowired
    public MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<Movimentacao> newMovimentacao(@RequestBody Movimentacao novaMovimentacao) {
        Movimentacao movimentacaoInserida = movimentacaoService.newMovimentacao(novaMovimentacao);

        if(movimentacaoInserida == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoInserida);
    }

    @GetMapping
    public ResponseEntity<List<Movimentacao>> getAll() {
        List<Movimentacao> listaMovimentacao = movimentacaoService.getAll();

        if(listaMovimentacao == null || listaMovimentacao.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaMovimentacao);
    }

}
