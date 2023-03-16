package br.gama.itau.projetogama04.util;

import br.gama.itau.projetogama04.model.Movimentacao;

public class GenerateMovimentacao {

    //operaçãoData não reconhece o tipo de valor da Data.

    public static Movimentacao movimentacaoValida() {
        return Movimentacao.builder()
            .numSeq(1)
            .valor(100)
            .tipoOperacao(200)
            .build();
    }

    public static Movimentacao novaMovimentacaoToSave() {
        return Movimentacao.builder()
        .valor(100)
        .tipoOperacao(200)
        .build();
    }
}
