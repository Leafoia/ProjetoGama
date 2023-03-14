package br.gama.itau.projetogama04.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gama.itau.projetogama04.model.Movimentacao;

public interface MovimentacaoRepo extends CrudRepository<Movimentacao, Long> {
    @Query("select c from Conta c join c.movimentacao where c.numeroConta = :numeroConta") 
    //faz o join do Conta com a Movimentação onde usamos o numero da conta como parâmetro

    List<Movimentacao> getMovimentacaoByConta(long numeroConta);
}
