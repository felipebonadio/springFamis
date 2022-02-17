package br.com.famis.controllerRestMock;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.famis.controller.ColaboradorController;
import br.com.famis.model.Colaborador;
import br.com.famis.model.Endereco;
import br.com.famis.model.Restaurante;
import br.com.famis.service.FamisService;

@WebMvcTest(ColaboradorController.class)
public class ColaboradorControlladorTeste {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FamisService famisService;

    DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm");

    Colaborador colab = new Colaborador();
    Endereco endereco = new Endereco();
    Restaurante restaurante = new Restaurante();

    @BeforeEach
    public void init() {
        colab.setId(UUID.fromString("da9d8cc7-9b92-45f6-baed-454f7ec826f8"));
        colab.setCpf("cpf");
        colab.setNome("Teste");
        colab.setSobrenome("Integracao");
        colab.setEmail("email");
        colab.setSenha("senha");
        colab.setFuncao("funcao");
        colab.setEndereco(endereco);
        colab.setRestaurante(restaurante);
        colab.setTelefone("telefone");

        endereco.setId(UUID.fromString("be047c42-2b46-4963-8a5c-97a692c7c36d"));
        endereco.setBairro("bairro");
        endereco.setCep("cep");
        endereco.setLocalidade("localidade");
        endereco.setLogradouro("logradouro");
        endereco.setNumero("numero");
        endereco.setUf("uf");

        restaurante.setId(UUID.fromString("0966177e-e405-4c20-9c44-84ae1004de05"));
        restaurante.setCnpj("cnpj");
        restaurante.setEndereco(endereco);
        restaurante.setMesa(2);
        restaurante.setNome("nome");
        restaurante.setTelefone("telefone");

    }

    @Test
    @DisplayName("Teste do encontrar colaborador por ID")
    void deveRetornarUmColaboradorPorId() throws Exception {

        when(famisService.findColaboradorById(UUID.fromString("da9d8cc7-9b92-45f6-baed-454f7ec826f8")))
                .thenReturn(Optional.of(colab));

        ObjectMapper mapper = new ObjectMapper();
        String encontrarColaborador = mapper.writeValueAsString(colab);

        mockMvc.perform(get("/colaboradores/" + colab.getId()))
                .andExpect(content().json(encontrarColaborador))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar um Not Found ao procurar colaborador por ID")
    void deveRetornarUmNotFoundColaboradorPorId() throws Exception {

        when(famisService.findColaboradorById(UUID.fromString("f9592bc4-a883-43be-bd2e-e8af53e3126c")))
                .thenReturn(Optional.of(colab));

        mockMvc.perform(get("/colaboradores/" + colab.getId()))
                .andExpect(status().isNotFound());
    }
}