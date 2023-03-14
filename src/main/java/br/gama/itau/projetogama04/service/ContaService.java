package br.gama.itau.projetogama04.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gama.itau.projetogama04.exception.NotFoundException;
import br.gama.itau.projetogama04.model.Cliente;
import br.gama.itau.projetogama04.repo.ClienteRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContaService {
    
    private final ClienteRepo repo;

    public Cliente getById(long idCliente) {

        Optional<Cliente> clienteOptional = repo.findById(idCliente);

        if(clienteOptional.isEmpty()) {
            throw new NotFoundException("Cliente não encontrado.");
        }
        Cliente clienteEncontrado = clienteOptional.get();
        return clienteEncontrado;
        
    }


}
