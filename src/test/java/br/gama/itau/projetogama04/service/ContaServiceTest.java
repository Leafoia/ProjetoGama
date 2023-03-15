package br.gama.itau.projetogama04.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.gama.itau.projetogama04.exception.NotFoundException;
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

    @Test
    public void getById_throwException_whenIdNotExist() {
        Conta novaConta = GenerateConta.novaContaToSave();

        // verifica se uma exception do tipo NotFoundException é lançada
        // () -> { } é uma chamada de método anônimo
        assertThrows(NotFoundException.class, () -> {
            contaService.getById(novaConta.getNumeroConta());
        });  //Confirmar com o professor.
    }

    @Test
    public void alteraDadosConta_returnAlteraDadosConta_whenContaValida() {
        // preparação
        // BDDMOckito está nos ajudando a simular, como um duplê, o que a classe
        // da dependência deveria fazer.
        // Neste exemplo, quando o método findById for chamado, o comportamento simulado
        // retorna os dados de um veículo válido que preparamos para o dublê (simulação)
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(GenerateConta.contaValida()));

        BDDMockito.when(repo.save(ArgumentMatchers.any(Conta.class)))
                .thenReturn(GenerateConta.contaValida2());

        Conta contaParaAlterar = GenerateConta.contaValida2();

        // ação
        Conta contaAtualizada = contaService.alterarDados(1D, 2L, contaParaAlterar);
        //A syntaxe do saldo está correta?

        // verificação
        assertThat(contaAtualizada).isNotNull();
        assertThat(contaAtualizada.getNumeroConta()).isEqualTo(2L);
        assertThat(contaAtualizada.getAgencia()).isEqualTo(contaParaAlterar.getAgencia());

        // verifica se o método save foi chamado 1 vez
        verify(repo, Mockito.times(1)).save(contaParaAlterar);
    }

}
