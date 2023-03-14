package br.gama.itau.projetogama04.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gama.itau.projetogama04.dto.ClienteDTO;
import br.gama.itau.projetogama04.exception.NotFoundException;
import br.gama.itau.projetogama04.model.Cliente;
import br.gama.itau.projetogama04.repo.ClienteRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepo repo;

    public Cliente getById(long idCliente) {

        Optional<Cliente> clienteOptional = repo.findById(idCliente);

        if(clienteOptional.isEmpty()) {
            throw new NotFoundException("Cliente não encontrado.");
        }
        Cliente clienteEncontrado = clienteOptional.get();
        return clienteEncontrado;
        
    }
    public List<ClienteDTO> getAll() {
        List<Cliente> listaClientes = (List<Cliente>) repo.findAll();

        List<ClienteDTO> listaClientesDTO = new ArrayList<>();

        for (Cliente cliente : listaClientes) {
            listaClientesDTO.add(new ClienteDTO(cliente));
        }
        return listaClientesDTO;
    }

    public Cliente newCliente(Cliente novoCliente) {
        if(novoCliente.getIdCliente() > 0) {
            return null;
        }
        Cliente clienteInserido = repo.save(novoCliente);
        return clienteInserido;
    }
	public Cliente newVeiculo(Cliente novoCliente) {
		return null;
	}



}
