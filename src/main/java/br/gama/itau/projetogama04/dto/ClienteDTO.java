package br.gama.itau.projetogama04.dto;

import br.gama.itau.projetogama04.model.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
    public class ClienteDTO {
        private String nomeCliente;
        private String cpfCliente;
        private String telefoneCliente;

        public ClienteDTO(Cliente cliente) {

            this.nomeCliente = cliente.getNomeCliente();
            this.cpfCliente = cliente.getCpfCliente();
            this.telefoneCliente = cliente.getTelefoneCliente();
        }


    }
    
