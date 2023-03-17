package br.gama.itau.projetogama04.integration;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

import br.gama.itau.projetogama04.model.Cliente;
import br.gama.itau.projetogama04.repo.ClienteRepo;
import br.gama.itau.projetogama04.util.GenerateCliente;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper ObjectMapper;

    @Autowired
    private ClienteRepo clienteRepo;
    
    @BeforeEach
    public void setup() {
        clienteRepo.deleteAll();
    }

    @Test
    public void newCliente_returnClienteInserido_whenDadosClienteValido() throws Exception {
        Cliente novoCliente = GenerateCliente.novoClienteToSave();

        ResultActions resposta = mockMvc.perform(post("/cliente")
                        .content(ObjectMapper.writeValueAsString(novoCliente))
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpfCliente", CoreMatchers.is(novoCliente.getCpfCliente())));
    }

    @Test
    public void getById_returnCliente_whenIdExist() throws Exception {
        Cliente novoCliente = GenerateCliente.novoClienteToSave();

        Cliente clienteCriado = clienteRepo.save(novoCliente);

        // ação
        ResultActions resposta = mockMvc.perform(get("/cliente/{idCliente}", clienteCriado.getIdCliente())
                .contentType(MediaType.APPLICATION_JSON));

        // verificar os resultados
        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.cpfCliente", CoreMatchers.is(clienteCriado.getCpfCliente())));
    }

}