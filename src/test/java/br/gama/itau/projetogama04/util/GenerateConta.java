package br.gama.itau.projetogama04.util;

import br.gama.itau.projetogama04.model.Conta;

public class GenerateConta {

    public static Conta contaValida() {
        return Conta.builder()
            .numeroConta(1)
            .agencia(4002)
            .tipoConta(1)
            .saldo(200)
            .build();
    }

    public static Conta novaContaToSave() {
        return Conta.builder()
            
            .agencia(4002)
            .tipoConta(1)
            .saldo(200)
            .build();
    }

}
