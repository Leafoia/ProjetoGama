package br.gama.itau.projetogama04.repo;

import org.springframework.data.repository.CrudRepository;

import br.gama.itau.projetogama04.model.Conta;

public interface ContaRepo extends CrudRepository<Conta, Long> {
    
}
