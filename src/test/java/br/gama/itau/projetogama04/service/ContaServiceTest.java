package br.gama.itau.projetogama04.service;

import static org.mockito.Mockito.verify;
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


}
