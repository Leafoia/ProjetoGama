package br.gama.itau.projetogama04.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.gama.itau.projetogama04.dto.ClienteDTO;
import br.gama.itau.projetogama04.exception.NotFoundException;
import br.gama.itau.projetogama04.model.Cliente;
import br.gama.itau.projetogama04.repo.ClienteRepo;
import br.gama.itau.projetogama04.util.GenerateCliente;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    
    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepo repo;

    @Test
    public void getById_returnCliente_whenIdExist() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(GenerateCliente.clienteValido()));

        Cliente clienteEncontrado = clienteService.getById(1L);

        assertThat(clienteEncontrado)
                .isNotNull();
        assertThat(clienteEncontrado.getIdCliente())
                .isGreaterThan(0);
        assertThat(clienteEncontrado.getCpfCliente())
                .isEqualTo(GenerateCliente.clienteValido().getCpfCliente())
                .isNotEmpty(); 
    }

    @Test
    public void getAll_returnListClientes_whenClienteExist() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(GenerateCliente.clienteValido());

        BDDMockito.when(repo.findAll()).thenReturn(clientes);

        List<ClienteDTO> listaClientes = clienteService.getAll();

        assertThat(listaClientes).isNotNull();
        assertThat(listaClientes).isNotEmpty();
        // testa o Id do primeiro elemento (cliente) da lista
        assertThat(listaClientes.get(0).getIdCliente()).isEqualTo(GenerateCliente.clienteValido().getIdCliente());
    }
    @Test
    public void getById_throwException_whenIdNotExist() {
        Cliente novoCliente = GenerateCliente.novoClienteToSave();

        // verifica se uma exception do tipo NotFoundException é lançada
        // () -> { } é uma chamada de método anônimo
        assertThrows(NotFoundException.class, () -> {
            clienteService.getById(novoCliente.getIdCliente());
        });  //Confirmarmado.
    }

    @Test
    public void newCliente_returnNull_whenClienteInvalido() {
        // Não precisa de Mock pois o método save do repo não será chamado
        // preparação
        Cliente clienteValido = GenerateCliente.clienteValido();

        // ação
        Cliente clienteRetorna = clienteService.newCliente(clienteValido);

        // verificação
        assertThat(clienteRetorna).isNull();

        // verifica que o método save não foi chamado
        verify(repo, Mockito.times(0)).save(clienteValido);
    }

    
    @Test
    public void newCliente_returnNewCliente_whenClienteValido() {
        // preparação
        BDDMockito.when(repo.save(ArgumentMatchers.any(Cliente.class)))
                .thenReturn(GenerateCliente.clienteValido());

        Cliente novoCliente = GenerateCliente.novoClienteToSave();

        // ação
        Cliente clienteCriacao = clienteService.newCliente(novoCliente);

        // verificação
        assertThat(clienteCriacao).isNotNull(); //testa as informações do cliente
        assertThat(clienteCriacao.getIdCliente()).isPositive();
        assertThat(clienteCriacao.getCpfCliente()).isEqualTo(novoCliente.getCpfCliente());

        // verifica se o método save foi chamado 1 vez
        verify(repo, Mockito.times(1)).save(novoCliente);
    }


}
