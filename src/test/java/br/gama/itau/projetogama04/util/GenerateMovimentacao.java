package br.gama.itau.projetogama04.util;

import java.time.LocalDate;

import br.gama.itau.projetogama04.model.Movimentacao;

public class GenerateMovimentacao {



    public static Movimentacao movimentacaoValida() {
        return Movimentacao.builder()
            .numSeq(1)
            .valor(100)
            .dataOperacao(LocalDate.of(2023,03,15))
            .tipoOperacao(2)
            .build();
    }

    public static Movimentacao movimentacaoValida2() {
        return Movimentacao.builder()
            .numSeq(2)
            .valor(200)
            .dataOperacao(LocalDate.of(2023,03,15))
            .tipoOperacao(2)
            .build();
    }

    public static Movimentacao novaMovimentacaoToSave() {
        return Movimentacao.builder()
        .valor(100)
        .dataOperacao(LocalDate.of(2023,03,15))
        .tipoOperacao(2)
        .build();
    }
}
