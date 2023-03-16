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

import br.gama.itau.projetogama04.model.Movimentacao;
import br.gama.itau.projetogama04.repo.MovimentacaoRepo;
import br.gama.itau.projetogama04.util.GenerateMovimentacao;

@ExtendWith(MockitoExtension.class)
public class MovimentacaoServiceTest {
    
    @InjectMocks
    private MovimentacaoService movimentacaoService;

    @Mock
    private MovimentacaoRepo repo;

    @Test
    public void newMovimentacao_returnNewMovimentacao_whenMovimentacaoValida() {
        // preparação
        BDDMockito.when(repo.save(ArgumentMatchers.any(Movimentacao.class)))
                .thenReturn(GenerateMovimentacao.movimentacaoValida());

        Movimentacao novaMovimentacao = GenerateMovimentacao.novaMovimentacaoToSave();

        // ação
        Movimentacao movimentacaoCriada = movimentacaoService.newMovimentacao(novaMovimentacao);

        // verificação
        assertThat(movimentacaoCriada).isNotNull();
        assertThat(movimentacaoCriada.getNumSeq()).isPositive();
        assertThat(movimentacaoCriada.getTipoOperacao()).isEqualTo(novaMovimentacao.getTipoOperacao());

        // verifica se o método save foi chamado 1 vez
        verify(repo, Mockito.times(1)).save(novaMovimentacao);
    }
}
