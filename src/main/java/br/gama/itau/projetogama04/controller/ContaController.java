package br.gama.itau.projetogama04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.gama.itau.projetogama04.model.Conta;

import br.gama.itau.projetogama04.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
    
    @Autowired
    private ContaService contaService;
   

    @GetMapping("/{numeroConta}")
    public ResponseEntity<Conta> getById(@PathVariable Long numeroConta) {
        Conta conta = contaService.getById(numeroConta);
        if(conta == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(conta);
    
    }   

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Conta>> getContasByCliente(@PathVariable long idCliente) {
        
        List<Conta> conta = contaService.buscarContasPeloCliente(idCliente);
        if(conta == null) {
            return ResponseEntity.notFound().build();
        } 
        
        return ResponseEntity.ok(conta);
    }

    @PostMapping
    public ResponseEntity<Conta> newConta(@RequestBody Conta novaConta) {
        Conta contaInserida = contaService.newConta(novaConta);

        if(contaInserida == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(contaInserida);
    }


}
