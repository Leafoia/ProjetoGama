package br.gama.itau.projetogama04.model;

import java.time.LocalDateTime;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Movimentacao {
    
    private long num_seq;
    private LocalDateTime data_operacao;
    private double valor;
    private int tipo_operacao;
    private String descricao;


    @ManyToOne
    @JoinColumn(name = "numero_conta")
    private Conta conta;
}
