package br.gama.itau.projetogama04.repo;

import org.springframework.data.repository.CrudRepository;

import br.gama.itau.projetogama04.model.Movimentacao;

public interface MotimentacaoRepo extends CrudRepository<Movimentacao, Long> {
    
}
