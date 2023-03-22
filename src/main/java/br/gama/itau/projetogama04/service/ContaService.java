package br.gama.itau.projetogama04.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gama.itau.projetogama04.exception.NotFoundException;
import br.gama.itau.projetogama04.model.Cliente;
import br.gama.itau.projetogama04.model.Conta;

import br.gama.itau.projetogama04.repo.ContaRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContaService {
    
    private final ContaRepo repo;

    public Conta newConta(Conta novaConta) {
        if(novaConta.getNumeroConta() > 0) {
            return null;
        }
        Conta contaInserida = repo.save(novaConta);
        return contaInserida;
    }

    public Conta getById(long numeroConta) {

        Optional<Conta> contaOptional = repo.findById(numeroConta);

        if(contaOptional.isEmpty()) {
            throw new NotFoundException("Conta não encontrada.");
        }
        Conta contaEncontrada = contaOptional.get();
        return contaEncontrada;
        
    }

    public Conta alterarDados (double saldo,long numeroConta ,Conta conta) {
        Optional<Conta> contaOptional = repo.findById(numeroConta);

        if(contaOptional.isEmpty()) {
            return null;
        }
        conta.setSaldo(saldo);
        Conta contaAtualizada = repo.save(conta);
        return contaAtualizada;
    }

    public List<Conta> buscarContasPeloCliente (long idCliente) {

        return repo.findByCliente(Cliente.builder().idCliente(idCliente).build());
        
    }
    
    
}
