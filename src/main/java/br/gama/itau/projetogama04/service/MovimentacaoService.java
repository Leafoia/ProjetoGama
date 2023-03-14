package br.gama.itau.projetogama04.service;

import org.springframework.stereotype.Service;

import br.gama.itau.projetogama04.model.Movimentacao;
import br.gama.itau.projetogama04.repo.MovimentacaoRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {
    private final MovimentacaoRepo repo;

    public Movimentacao newMovimentacao(Movimentacao novaMovimentacao) {
        if(novaMovimentacao.getNumSeq() > 0) {
            return null;
        }
        Movimentacao movimentacaoRealizada = repo.save(novaMovimentacao);
        return movimentacaoRealizada;
    }

    
}
