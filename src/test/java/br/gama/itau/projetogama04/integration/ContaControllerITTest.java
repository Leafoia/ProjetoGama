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

import br.gama.itau.projetogama04.model.Conta;
import br.gama.itau.projetogama04.repo.ContaRepo;
import br.gama.itau.projetogama04.util.GenerateConta;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ContaControllerITTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper ObjectMapper;

    @Autowired
    private ContaRepo contaRepo;

    @BeforeEach
    public void setup() {
        contaRepo.deleteAll();
    }
    @Test
    public void getById_returnConta_whenNumeroContaExist() throws Exception {
        Conta novaConta = GenerateConta.novaContaToSave();

        Conta contaCriada = contaRepo.save(novaConta);

        // ação
        ResultActions resposta = mockMvc.perform(get("/contas/{numeroConta}", contaCriada.getNumeroConta())
                .contentType(MediaType.APPLICATION_JSON));

        // verificar os resultados
        resposta.andExpect(status().isOk())
        .andExpect(jsonPath("$.agencia", CoreMatchers.is(contaCriada.getAgencia())));
    }
}
