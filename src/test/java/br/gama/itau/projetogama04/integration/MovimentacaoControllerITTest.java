package br.gama.itau.projetogama04.integration;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

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
import br.gama.itau.projetogama04.model.Conta;
import br.gama.itau.projetogama04.model.Movimentacao;
import br.gama.itau.projetogama04.repo.ClienteRepo;
import br.gama.itau.projetogama04.repo.ContaRepo;
import br.gama.itau.projetogama04.repo.MovimentacaoRepo;
import br.gama.itau.projetogama04.util.GenerateCliente;
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

    @Autowired
    private ContaRepo contaRepo;
    @Autowired
    private ClienteRepo clienteRepo;
    
    @BeforeEach
    public void setup() {
        movimentacaoRepo.deleteAll();
        contaRepo.deleteAll();
        clienteRepo.deleteAll();
    }

    @Test
    public void newMovimentacao_returnMovimentacaoInserida_whenMovimentacaoValida() throws Exception {
        Movimentacao novaMovimentacao = GenerateMovimentacao.novaMovimentacaoToSave(1);

        ResultActions resposta = mockMvc.perform(post("/movimentacao")
                        .content(ObjectMapper.writeValueAsString(novaMovimentacao))
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isCreated())
                .andExpect(jsonPath("$.tipoOperacao", CoreMatchers.is(novaMovimentacao.getTipoOperacao())));
    }
    
    @Test
    public void buscarMovimentacoesByConta_returnListMovimentacoes_whenSuccess() throws Exception {
        Cliente cliente = GenerateCliente.novoClienteToSave();
        cliente = clienteRepo.save(cliente);
        Conta conta = GenerateConta.novaContaToSave(cliente.getIdCliente());
        conta = contaRepo.save(conta);
        List<Movimentacao> movimentacoes = new ArrayList<>();
        movimentacoes.add(GenerateMovimentacao.novaMovimentacaoToSave(conta.getNumeroConta()));
        movimentacoes.add(GenerateMovimentacao.novaMovimentacaoToSave2(conta.getNumeroConta()));
        movimentacaoRepo.saveAll(movimentacoes);
        // ação
        ResultActions resposta = mockMvc.perform(get("/movimentacao/{numeroConta}", conta.getNumeroConta())
        .contentType(MediaType.APPLICATION_JSON));

        // verificações
        resposta.andExpect(status().isOk()) 
                .andExpect(jsonPath("$.size()", CoreMatchers.is(movimentacoes.size())))
                .andExpect(jsonPath("$[0].tipoOperacao", CoreMatchers.is(GenerateMovimentacao.movimentacaoValida().getTipoOperacao())));
    }
}
