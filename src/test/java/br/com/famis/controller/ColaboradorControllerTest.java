package br.com.famis.controller;

import br.com.famis.model.Endereco;
import br.com.famis.model.Colaborador;
import br.com.famis.model.Restaurante;
import br.com.famis.service.FamisService;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import io.restassured.http.ContentType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebMvcTest
class ColaboradorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ColaboradorController colaboradorController;

    @Autowired
    @MockBean
    private FamisService famisService;

    Colaborador colaborador = new Colaborador();
    Colaborador savedColaborador = new Colaborador();
    Colaborador updatedColaborador = new Colaborador();
    List<Colaborador> colaboradors = new ArrayList<>();
    Restaurante restaurante = new Restaurante();
    Endereco endereco = new Endereco();
    DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @BeforeEach
    private void init(){
        standaloneSetup(this.colaboradorController);

        endereco.setId(UUID.fromString("32b5fd62-2e38-4561-a97d-4ff8fc1da29a"));
        endereco.setCep("17340170");
        endereco.setPlace("Rua de lá");
        endereco.setDistrict("Vila de cá");
        endereco.setNumber("1111");
        endereco.setCity("Fim do mundo");
        endereco.setState("Botas");

        restaurante.setId(UUID.fromString("7602afd1-6de6-4924-ba32-696fa19d886b"));
        restaurante.setName("Restaurante");
        restaurante.setCnpj("145631346");
        restaurante.setPhone("1454878484");
        restaurante.setOpenTime(LocalTime.parse("18:00",hourFormatter));
        restaurante.setCloseTime(LocalTime.parse("24:00",hourFormatter));
        restaurante.setConsumer(20);
        restaurante.setEndereco(endereco);

        colaborador.setId(UUID.fromString("e554cd0f-a050-4fac-8acc-8b33c5a7b00c"));
        colaborador.setName("Felipe");
        colaborador.setLastName("Bonadio");
        colaborador.setCpf("54545454");
        colaborador.setRole("Qualquer");
        colaborador.setPhone("151515151");
        colaborador.setRestaurante(restaurante);
        colaborador.setEndereco(endereco);
        colaborador.setEmail("Email");
        colaborador.setPassword("Password");

        savedColaborador.setId(UUID.fromString("f562f82d-16b3-4c9f-810e-362fbd4a8f80"));
        savedColaborador.setName("Felipe");
        savedColaborador.setLastName("Bonadio");
        savedColaborador.setCpf("54545454");
        savedColaborador.setRole("Qualquer");
        savedColaborador.setPhone("151515151");
        savedColaborador.setRestaurante(restaurante);
        savedColaborador.setEndereco(endereco);
        savedColaborador.setEmail("Email");
        savedColaborador.setPassword("Password");

        updatedColaborador.setId(UUID.fromString("e554cd0f-a050-4fac-8acc-8b33c5a7b00c"));
        updatedColaborador.setName("Guilherme");
        updatedColaborador.setLastName("Bonadio");
        updatedColaborador.setCpf("54545454");
        updatedColaborador.setRole("Qualquer");
        updatedColaborador.setPhone("151515151");
        updatedColaborador.setRestaurante(restaurante);
        updatedColaborador.setEndereco(endereco);
        updatedColaborador.setEmail("Email");
        updatedColaborador.setPassword("Password");

        colaboradors.add(colaborador);
    }

    @Test
    @DisplayName("Teste para retornar um colaborador por id")
    void getCollaborator() throws Exception {

        when(this.famisService.findColaboradorById(UUID.fromString("e554cd0f-a050-4fac-8acc-8b33c5a7b00c")))
                .thenReturn(Optional.ofNullable(colaborador));

        given()
                .accept(ContentType.JSON)
        .when()
                .get("collaborators/" + colaborador.getId())
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Teste para retornar um colaborador com NOT FOUND")
    public void getCollaboratorNotFound() {

        when(this.famisService.findColaboradorById(UUID.fromString("1e72af27-d738-40dc-9b1e-a562f6d5eeab")))
                .thenReturn(null);

        given()
                .accept(ContentType.JSON)
        .when()
                .get("/collaborators/" + colaborador.getId(), UUID.fromString("1e72af27-d738-40dc-9b1e-a562f6d5eeab"))
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Teste para retornar todos os colaboradores.")
    void getCollaborators() {
        when(this.famisService.findAllColaboradores()).thenReturn(colaboradors);

        given()
                .accept(ContentType.JSON)
        .when()
                .get("/collaborators")
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Teste para salvar um colaborador.")
    void saveCollaborator() throws Exception {

        given(famisService.saveColaborador(savedColaborador)).willReturn(savedColaborador);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String savedCollaboratorAsJSON = mapper.writeValueAsString(savedColaborador);
        this.mockMvc.perform(post("/collaborators")
                        .content(savedCollaboratorAsJSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isCreated());
    }

//    @Test
//    void updateCollaborator() throws Exception {
//
//        given(famisService.updateCollaborator(collaborator)).willReturn(collaborator);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//
//        String updatedCollaboratorComoJSON = mapper.writeValueAsString(collaborator);
//
//        mockMvc.perform(put("/collaborator")
//                        .content(updatedCollaboratorComoJSON)
//                        .accept(MediaType.APPLICATION_JSON_VALUE)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                        .andExpect(status().isOk())
//                        .andExpect(content().json(updatedCollaboratorComoJSON));
//
//    }

    @Test
    void deleteById() {
    }
}