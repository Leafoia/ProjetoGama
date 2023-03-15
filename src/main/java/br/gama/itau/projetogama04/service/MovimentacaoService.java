package br.gama.itau.projetogama04.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gama.itau.projetogama04.dto.MovimentacaoDTO;
import br.gama.itau.projetogama04.exception.NotFoundException;
import br.gama.itau.projetogama04.model.Conta;
import br.gama.itau.projetogama04.model.Movimentacao;
import br.gama.itau.projetogama04.repo.ContaRepo;
import br.gama.itau.projetogama04.repo.MovimentacaoRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {
    private final MovimentacaoRepo repo;
    private final ContaRepo repoConta;

    public Movimentacao newMovimentacao(Movimentacao novaMovimentacao) {
        if (novaMovimentacao.getNumSeq() > 0) {
            return null;
        }
        Movimentacao movimentacaoRealizada = repo.save(novaMovimentacao);
        return movimentacaoRealizada;
    }

    public List<MovimentacaoDTO> getMovId(long numeroConta) {

        Optional<Conta> contaOptional = repoConta.findById(numeroConta);

        if(contaOptional.isEmpty()) {
            throw new NotFoundException("Conta não encontrada.");
        }
        Conta contaEncontrada = contaOptional.get();
        List<MovimentacaoDTO> listaDTO = new ArrayList<>();
        List<Movimentacao> listaMov = contaEncontrada.getMovimentacoes();

        for (Movimentacao movimentacao : listaMov) {
            listaDTO.add(new MovimentacaoDTO(movimentacao));
        }
        return listaDTO;
        
    }


}
