package br.gama.itau.projetogama04.dto;

import java.time.LocalDate;

import br.gama.itau.projetogama04.model.Movimentacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoDTO {
    private long numSeq;
    private LocalDate dataOperacao;
    private double valor;
    private int tipoOperacao;

    public MovimentacaoDTO(Movimentacao mov) {
        this.numSeq = mov.getNumSeq();
        this.dataOperacao = mov.getDataOperacao();
        this.valor = mov.getValor();
        this.tipoOperacao = mov.getTipoOperacao();
    }

    
}
