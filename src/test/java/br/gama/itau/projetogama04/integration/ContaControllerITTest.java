package br.gama.itau.projetogama04.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import br.gama.itau.projetogama04.repo.ClienteRepo;
import br.gama.itau.projetogama04.repo.ContaRepo;
import br.gama.itau.projetogama04.repo.MovimentacaoRepo;
import br.gama.itau.projetogama04.util.GenerateCliente;
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

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private MovimentacaoRepo movimentacaoRepo;

    

    @BeforeEach
    public void setup() {
        movimentacaoRepo.deleteAll();
        contaRepo.deleteAll();
        clienteRepo.deleteAll();
        
    }
    @Test
    public void getById_returnConta_whenNumeroContaExist() throws Exception {
        Cliente cliente = GenerateCliente.novoClienteToSave();
        cliente = clienteRepo.save(cliente);
        Conta novaConta = GenerateConta.novaContaToSave(cliente.getIdCliente());

        Conta contaCriada = contaRepo.save(novaConta);

        // ação
        ResultActions resposta = mockMvc.perform(get("/contas/{numeroConta}", contaCriada.getNumeroConta())
                .contentType(MediaType.APPLICATION_JSON));

        // verificar os resultados
        resposta.andExpect(status().isOk())
        .andExpect(jsonPath("$.agencia", CoreMatchers.is(contaCriada.getAgencia())));
    }

    @Test
    public void newConta_returnContaInserida_whenDadosContaValida() throws Exception {
        Cliente cliente = GenerateCliente.novoClienteToSave();
        cliente = clienteRepo.save(cliente);
        Conta novaConta = GenerateConta.novaContaToSave(cliente.getIdCliente());

        ResultActions resposta = mockMvc.perform(post("/contas")
                        .content(ObjectMapper.writeValueAsString(novaConta))
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isCreated())
                .andExpect(jsonPath("$.agencia", CoreMatchers.is(novaConta.getAgencia())));
    }

    @Test
    public void buscarContasByCliente_returnListContas_whenSuccess() throws Exception {
        Cliente cliente = GenerateCliente.novoClienteToSave();
        cliente = clienteRepo.save(cliente);
        List<Conta> contas = new ArrayList<>();
        contas.add(GenerateConta.novaContaToSave(cliente.getIdCliente()));
        contas.add(GenerateConta.novaContaToSave2(cliente.getIdCliente()));
        contaRepo.saveAll(contas);
        
        // ação
        ResultActions resposta = mockMvc.perform(get("/contas/cliente/{idCliente}", cliente.getIdCliente())
        .contentType(MediaType.APPLICATION_JSON));

        // verificações
        resposta.andExpect(status().isOk()) 
                .andExpect(jsonPath("$.size()", CoreMatchers.is(contas.size())))
                .andExpect(jsonPath("$[0].agencia", CoreMatchers.is(GenerateConta.contaValida().getAgencia())));

    }



}
