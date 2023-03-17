package br.gama.itau.projetogama04.util;

import java.util.ArrayList;
import java.util.List;

import br.gama.itau.projetogama04.dto.ContaDTO;
import br.gama.itau.projetogama04.model.Cliente;
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

    public static Conta contaValida2() {
        return Conta.builder()
            .numeroConta(2)
            .agencia(4004)
            .tipoConta(1)
            .saldo(200)
            .build();
    }

    public static Conta novaContaToSave(long numero) {
        return Conta.builder()
            
            .agencia(4002)
            .tipoConta(1)
            .saldo(200)
            .cliente(Cliente.builder().idCliente(numero).build())
            .build();
    }

    public static List<ContaDTO> listaValida() {

    List<ContaDTO> listaContasDTO = new ArrayList<>();
    return listaContasDTO;
    }
}
