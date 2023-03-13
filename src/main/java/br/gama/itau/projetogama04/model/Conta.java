package br.gama.itau.projetogama04.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Codigo")
    private long numeroConta;
   

    private int agencia;
    

    private int tipoConta;


    private double saldo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    //linka com o cliente
    @OneToMany(mappedBy = "conta")
    @JsonIgnoreProperties("conta")
    private List<Movimentacao> movimentacoes;
    //linka com a movimentação
}
