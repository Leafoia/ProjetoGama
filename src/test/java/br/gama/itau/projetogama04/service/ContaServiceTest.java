package br.gama.itau.projetogama04.service;

import static org.mockito.Mockito.verify;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.gama.itau.projetogama04.model.Conta;
import br.gama.itau.projetogama04.repo.ContaRepo;
import br.gama.itau.projetogama04.util.GenerateConta;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepo repo;

    @Test
    public void newConta_returnNewConta_whenContaValida() {
        // preparação
        BDDMockito.when(repo.save(ArgumentMatchers.any(Conta.class)))
                .thenReturn(GenerateConta.contaValida());

        Conta novaConta = GenerateConta.novaContaToSave();

        // ação
        Conta contaCriada = contaService.newConta(novaConta);

        // verificação
        assertThat(contaCriada).isNotNull();
        assertThat(contaCriada.getNumeroConta()).isPositive();
        assertThat(contaCriada.getAgencia()).isEqualTo(novaConta.getAgencia());

        // verifica se o método save foi chamado 1 vez
        verify(repo, Mockito.times(1)).save(novaConta);
    }

    @Test
    public void newConta_returnNull_whenContaInvalida() {
        // Não precisa de Mock pois o método save do repo não será chamado
        // preparação
        Conta contaValida = GenerateConta.contaValida();

        // ação
        Conta contaRetornada = contaService.newConta(contaValida);

        // verificação
        assertThat(contaRetornada).isNull();

        // verifica que o método save não foi chamado
        verify(repo, Mockito.times(0)).save(contaValida);
    }

    @Test
    public void getById_returnConta_whenIdExist() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(GenerateConta.contaValida()));

        Conta contaEncontrada = contaService.getById(1L);

        assertThat(contaEncontrada)
                .isNotNull();
        assertThat(contaEncontrada.getNumeroConta())
                .isGreaterThan(0);
        assertThat(contaEncontrada.getAgencia())
                .isEqualTo(GenerateConta.contaValida().getAgencia())
                .isNotNull(); //Não aceitava isNotEmpty(). Utilizamos isNotNull().
    }


}
