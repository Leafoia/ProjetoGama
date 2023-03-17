package br.gama.itau.projetogama04.service;

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


    public Movimentacao newMovimentacao(Movimentacao novaMovimentacao) {
        if (novaMovimentacao.getNumSeq() > 0) {
            return null;
        }
        Movimentacao movimentacaoRealizada = repo.save(novaMovimentacao);
        return movimentacaoRealizada;
    }

    public List<MovimentacaoDTO> getMovId(long numeroConta) {
        

        List<MovimentacaoDTO> listaDTO = new ArrayList<>();
     //   List<Movimentacao> listaMov = repo.getMovimentacaoByConta(numeroConta);
        List<Movimentacao> listaMov = repo.findByConta(Conta.builder().numeroConta(numeroConta).build());

        for (Movimentacao movimentacao : listaMov) {
            listaDTO.add(new MovimentacaoDTO(movimentacao));
        }
        return listaDTO;
        
    }


}
