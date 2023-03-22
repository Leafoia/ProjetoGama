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

import br.gama.itau.projetogama04.dto.ClienteDTO;
import br.gama.itau.projetogama04.model.Cliente;
import br.gama.itau.projetogama04.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll() {
        
        List<ClienteDTO> lista = clienteService.getAll();

        if(lista == null || lista.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long idCliente) {

        Cliente cliente = clienteService.getById(idCliente);
        if(cliente == null) {
            return ResponseEntity.notFound().build();
        }
        ClienteDTO clienteDTO = new ClienteDTO(cliente);
        return ResponseEntity.ok().body(clienteDTO);
    
    }   

    @PostMapping
    public ResponseEntity<Cliente> newCliente(@RequestBody Cliente novoCliente) {
        
        Cliente clienteInserido = clienteService.newCliente(novoCliente);

        if(clienteInserido == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteInserido); 
    }
}
