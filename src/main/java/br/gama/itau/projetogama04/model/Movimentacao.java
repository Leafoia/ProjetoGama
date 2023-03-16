package br.gama.itau.projetogama04.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigoMov")
    private long numSeq;
    

    private LocalDate dataOperacao;

    private double valor;

    private int tipoOperacao;

    @Column(length = 255)
    private String descricao;


    @ManyToOne
    @JoinColumn(name = "numeroConta")
    @JsonIgnoreProperties("movimentacao")
    private Conta conta;



}
