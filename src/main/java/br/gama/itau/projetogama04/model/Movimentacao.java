package br.gama.itau.projetogama04.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Código_Mov")
    private long num_seq;
    

    private LocalDateTime data_operacao;

    private double valor;

    private int tipo_operacao;

    @Column(length = 255)
    private String descricao;


    @ManyToOne
    @JoinColumn(name = "numero_conta")
    private Conta conta;
}
