package br.gama.itau.projetogama04.service;

//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import br.gama.itau.projetogama04.dto.MovimentacaoDTO;
import br.gama.itau.projetogama04.model.Conta;
import br.gama.itau.projetogama04.model.Movimentacao;

import br.gama.itau.projetogama04.repo.MovimentacaoRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {
    private final MovimentacaoRepo repo;
//    private final ContaService contaService;


    public Movimentacao newMovimentacao(Movimentacao novaMovimentacao) {
        if (novaMovimentacao.getNumSeq() > 0) {
            return null;
        }
        Movimentacao movimentacaoRealizada = repo.save(novaMovimentacao);
        return movimentacaoRealizada;
    }

    public List<MovimentacaoDTO> getMovId(long numeroConta) {
        

        List<MovimentacaoDTO> listaDTO = new ArrayList<>();
        List<Movimentacao> listaMov = repo.findByConta(Conta.builder().numeroConta(numeroConta).build());

        for (Movimentacao movimentacao : listaMov) {
            listaDTO.add(new MovimentacaoDTO(movimentacao));
        }
        return listaDTO;
        
    }

    // public boolean transferirValores(long numeroContaOrigem, long numeroContaDestino, double valor) {
    //     Conta contaOrigem = contaService.getById(numeroContaOrigem);
    //     Conta contaDestino = contaService.getById(numeroContaDestino);
    //     if (valor <= 0) {
    //         return false;
    //     } else {
    //         contaService.alterarDados(contaDestino.getSaldo() + valor, numeroContaDestino, contaDestino);
    //         contaService.alterarDados(contaOrigem.getSaldo() - valor, numeroContaOrigem, contaOrigem);

    //         repo.save(Movimentacao.builder().conta(contaOrigem).dataOperacao(LocalDate.now()).valor(valor).build());
    //         repo.save(Movimentacao.builder().conta(contaDestino).dataOperacao(LocalDate.now()).valor(valor).build());
    //         return true;
    //     }
        
    // }      FALTA OS TESTES
}
