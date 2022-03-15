package br.com.famis.controllerRestMock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import br.com.famis.dto.ColaboradorDto;
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

//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    private ColaboradorService colaboradorService;
//
//    DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm");
//
//    Colaborador colaborador = new Colaborador();
//    ColaboradorDto colaboradorDto = new ColaboradorDto();
//    Restaurante restaurante = new Restaurante();
//
//    @BeforeEach
//    public void init() {
//        colaborador.setId(1L);
//        colaborador.setCpf("cpf");
//        colaborador.setNome("Teste");
//        colaborador.setSobrenome("Integracao");
//        colaborador.setFuncao("funcao");
//        colaborador.setRestaurante(restaurante);
//        colaborador.setTelefone("telefone");
//
//        restaurante.setId(1L);
//        restaurante.setCnpj("cnpj");
//        restaurante.setNome("nome");
//        restaurante.setTelefone("telefone");
//    }
//
//    @Test
//    @DisplayName("Teste do encontrar colaborador por ID")
//    void deveRetornarUmColaboradorPorId() throws Exception {
//
//        when(colaboradorService.findColaboradorById(1L))
//                .thenReturn(Optional.of(colaboradorDto));
//
//        ObjectMapper mapper = new ObjectMapper();
//        String encontrarColaborador = mapper.writeValueAsString(colaboradorDto);
//
//        mockMvc.perform(get("/colaboradores/" + colaboradorDto.getId()))
//                .andExpect(content().json(encontrarColaborador))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("Deve retornar um Not Found ao procurar colaborador por ID inexistente")
//    void deveRetornarUmNotFoundColaboradorPorId() throws Exception {
//
//        when(colaboradorService.findColaboradorById(anyLong()))
//                .thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/colaboradores/f9592bc4-a883-43be-bd2e-e8af53e3126c"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("Teste de salvar um colaborador")
//    void deveSalvarUmColaborador() throws Exception{
//        when(colaboradorService.saveColaborador(colaboradorDto)).thenAnswer((i)-> i.getArgument(0));
//
//        ObjectMapper mapper = new ObjectMapper();
//        String salvarColaborador = mapper.writeValueAsString(colaboradorDto);
//
//        mockMvc.perform(post("/colaboradores")
//                .content(salvarColaborador)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(salvarColaborador))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    @DisplayName("Teste de criar um colaborador e depois apaga-lo")
//    void deveCriarUmColaboradorEDepoisApagar() throws Exception {
//
//        when(colaboradorService.saveColaborador(colaboradorDto)).thenAnswer((i)-> i.getArgument(0));
//        when(colaboradorService.findColaboradorById(colaboradorDto.getId())).thenReturn(Optional.ofNullable(colaboradorDto));
//
//        ObjectMapper mapper = new ObjectMapper();
//        String salvarColaborador = mapper.writeValueAsString(colaboradorDto);
//
//        mockMvc.perform(post("/colaboradores")
//                .content(salvarColaborador)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(salvarColaborador))
//                .andExpect(status().isCreated());
//
//        mockMvc.perform(get("/colaboradores/"+ colaboradorDto.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        mockMvc.perform(delete("/colaboradores/" + colaboradorDto.getId()))
//                .andExpect(status().isNoContent());
//    }
}