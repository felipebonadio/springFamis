package br.com.famis.controllerRestMock;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import br.com.famis.service.ColaboradorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.famis.controller.ColaboradorController;
import br.com.famis.model.Colaborador;
import br.com.famis.model.Restaurante;

@WebMvcTest(ColaboradorController.class)
public class ColaboradorControllerTeste {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ColaboradorService colaboradorService;

    DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm");

    Colaborador colab = new Colaborador();
    Restaurante restaurante = new Restaurante();

    @BeforeEach
    public void init() {
        colab.setId(UUID.fromString("da9d8cc7-9b92-45f6-baed-454f7ec826f8"));
        colab.setCpf("cpf");
        colab.setNome("Teste");
        colab.setSobrenome("Integracao");
        colab.setFuncao("funcao");
        colab.setRestaurante(restaurante);
        colab.setTelefone("telefone");

        restaurante.setId(UUID.fromString("0966177e-e405-4c20-9c44-84ae1004de05"));
        restaurante.setCnpj("cnpj");
        restaurante.setNome("nome");
        restaurante.setTelefone("telefone");
    }

    @Test
    @DisplayName("Teste do encontrar colaborador por ID")
    void deveRetornarUmColaboradorPorId() throws Exception {

        when(colaboradorService.findColaboradorById(UUID.fromString("da9d8cc7-9b92-45f6-baed-454f7ec826f8")))
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

        when(colaboradorService.findColaboradorById(UUID.fromString("f9592bc4-a883-43be-bd2e-e8af53e3126c")))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/colaboradores/f9592bc4-a883-43be-bd2e-e8af53e3126c"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Teste de salvar um colaborador")
    void deveSalvarUmColaborador() throws Exception{
        when(colaboradorService.saveColaborador(colab)).thenAnswer((i)-> i.getArgument(0));

        ObjectMapper mapper = new ObjectMapper();
        String salvarColaborador = mapper.writeValueAsString(colab);

        mockMvc.perform(post("/colaboradores")
                .content(salvarColaborador)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(salvarColaborador))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar um BadRequest ao salvar com parametros errados")
    void deveRetornarBadRequestAoSalvarUmColaborador() throws Exception{

        when(colaboradorService.saveColaborador(colab)).thenAnswer((i)-> i.getArgument(0));

        ObjectMapper mapper = new ObjectMapper();
        String salvarColabVazio= mapper.writeValueAsString(colab);

        mockMvc.perform(post("/colaboradores")
                .content(salvarColabVazio)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(salvarColabVazio))
                .andExpect(status().isBadRequest());
    }
}