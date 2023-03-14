package br.gama.itau.projetogama04.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCliente;

    @Column(length = 100, nullable = false)
    private String nomeCliente;

    @Column(length = 20, nullable = false, unique = true)
    private String cpfCliente;
    
    @Column(length = 20, nullable = false, unique = true)
    private String telefoneCliente;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnoreProperties("cliente")
    private List<Conta> contas;
}
