package br.gama.itau.projetogama04.dto;

import br.gama.itau.projetogama04.model.Conta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {

    private long numeroConta;
    private int agencia;
    private int tipoConta;
    private double saldo;

    public ContaDTO(Conta conta) {
        this.numeroConta = conta.getNumeroConta();
        this.agencia = conta.getAgencia();
        this.tipoConta = conta.getAgencia();
        this.saldo = conta.getSaldo();
    }

}
