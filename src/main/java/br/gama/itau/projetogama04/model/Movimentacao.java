package br.gama.itau.projetogama04.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodigoMov")
    private long numSeq;
    

    private LocalDateTime dataOperacao;

    private double valor;

    private int tipoOperacao;

    @Column(length = 255)
    private String descricao;


    @ManyToOne
    @JoinColumn(name = "numeroConta")
    @JsonIgnoreProperties("movimentacao")
    private Conta conta;
}
