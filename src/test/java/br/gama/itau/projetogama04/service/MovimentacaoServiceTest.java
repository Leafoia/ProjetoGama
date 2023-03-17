package br.gama.itau.projetogama04.service;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.gama.itau.projetogama04.dto.MovimentacaoDTO;
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

    @Test
    public void newMovimentacao_returnNull_whenMovimentacaoInvalida() {
        Movimentacao movimentacaoValida = GenerateMovimentacao.movimentacaoValida();

        // ação
        Movimentacao movimentacaoRetornada = movimentacaoService.newMovimentacao(movimentacaoValida);

        // verificação
        assertThat(movimentacaoRetornada).isNull();

        // verifica que o método save não foi chamado
        verify(repo, Mockito.times(0)).save(movimentacaoValida);
    }

    @Test
    public void buscarMovimentacoesByConta_returnListMovimentacoes_whenContaExist() {
        List<Movimentacao> movimentacoes = new ArrayList<>();
        movimentacoes.add(GenerateMovimentacao.movimentacaoValida());
        movimentacoes.add(GenerateMovimentacao.movimentacaoValida2());

        BDDMockito.when(repo.getMovimentacaoByConta(1L)).thenReturn(movimentacoes);

        List<MovimentacaoDTO> listaRecuperada = movimentacaoService.getMovId(1L);

        assertThat(listaRecuperada).isNotNull();
        assertThat(listaRecuperada).isNotEmpty();

        // testa o Id do primeiro elemento (paciente) da lista
        assertThat(listaRecuperada.get(0).getNumSeq()).isEqualTo(GenerateMovimentacao.movimentacaoValida().getNumSeq());
    }
}
