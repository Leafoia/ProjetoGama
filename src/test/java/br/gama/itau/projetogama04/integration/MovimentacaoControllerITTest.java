package br.gama.itau.projetogama04.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gama.itau.projetogama04.model.Movimentacao;
import br.gama.itau.projetogama04.repo.MovimentacaoRepo;
import br.gama.itau.projetogama04.util.GenerateConta;
import br.gama.itau.projetogama04.util.GenerateMovimentacao;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovimentacaoControllerITTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper ObjectMapper;

    @Autowired
    private MovimentacaoRepo movimentacaoRepo;

    @BeforeEach
    public void setup() {
        movimentacaoRepo.deleteAll();
    }
    
    @Test
    public void newMovimentacao_returnMovimentacaoInserida_whenMovimentacaoValida() throws Exception {
        Movimentacao novaMovimentacao = GenerateMovimentacao.novaMovimentacaoToSave();

        ResultActions resposta = mockMvc.perform(post("/movimentacao")
                        .content(ObjectMapper.writeValueAsString(novaMovimentacao))
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isCreated())
                .andExpect(jsonPath("$.tipoOperacao", CoreMatchers.is(novaMovimentacao.getTipoOperacao())));
    }
}
