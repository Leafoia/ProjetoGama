package br.gama.itau.projetogama04.util;

import br.gama.itau.projetogama04.model.Cliente;

public class GenerateCliente {
    public static Cliente clienteValido() {
        return Cliente.builder()
            .idCliente(1)
            .nomeCliente("Josicleude Ferreira")
            .cpfCliente("86579436895")
            .telefoneCliente("3197648246")
            .build();
    }

    public static Cliente novoClienteToSave() {
        return Cliente.builder()
            
            .nomeCliente("Josicleude Ferreira")
            .cpfCliente("86579436895")
            .telefoneCliente("3197648246")
            .build();
    }
}
