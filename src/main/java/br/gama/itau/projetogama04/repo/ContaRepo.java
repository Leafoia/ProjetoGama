package br.gama.itau.projetogama04.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gama.itau.projetogama04.model.Conta;

public interface ContaRepo extends CrudRepository<Conta, Long> {
    @Query("select c.contas from Cliente c join c.contas where c.idCliente = :idCliente") //faz o join do Cliente com a Conta onde usamos o idCliente como parâmetro

    List<Conta> getContasByCliente(long idCliente);
}
