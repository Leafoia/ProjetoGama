package br.gama.itau.projetogama04.util;

import java.time.LocalDate;

import br.gama.itau.projetogama04.model.Conta;
import br.gama.itau.projetogama04.model.Movimentacao;

public class GenerateMovimentacao {



    public static Movimentacao movimentacaoValida() {
        return Movimentacao.builder()
            .numSeq(1)
            .valor(100)
            .dataOperacao(LocalDate.of(2023,03,15))
            .tipoOperacao(2)
            .conta(Conta.builder().numeroConta(1).build())
            .build();
    }

    public static Movimentacao movimentacaoValida2() {
        return Movimentacao.builder()
            .numSeq(2)
            .valor(200)
            .dataOperacao(LocalDate.of(2023,03,15))
            .tipoOperacao(2)
            .conta(Conta.builder().numeroConta(1).build())
            .build();
    }

    public static Movimentacao novaMovimentacaoToSave(long numero) {
        return Movimentacao.builder()
        .valor(100)
        .dataOperacao(LocalDate.of(2023,03,15))
        .tipoOperacao(2)
        .conta(Conta.builder().numeroConta(numero).build())
        .build();
    }
    public static Movimentacao novaMovimentacaoToSave2(long numero) {
        return Movimentacao.builder()
        .valor(120)
        .dataOperacao(LocalDate.of(2022,03,15))
        .tipoOperacao(1)
        .conta(Conta.builder().numeroConta(numero).build())
        .build();
    }
}
