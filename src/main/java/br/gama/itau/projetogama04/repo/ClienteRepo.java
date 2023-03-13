package br.gama.itau.projetogama04.repo;

import org.springframework.data.repository.CrudRepository;

import br.gama.itau.projetogama04.model.Cliente;

public interface ClienteRepo extends CrudRepository<Cliente, Long> {
    
}
