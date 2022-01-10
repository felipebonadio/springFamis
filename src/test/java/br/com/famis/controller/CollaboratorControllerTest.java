package br.com.famis.controller;

import br.com.famis.model.Address;
import br.com.famis.model.Collaborator;
import br.com.famis.model.Restaurant;
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
class CollaboratorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CollaboratorController collaboratorController;

    @Autowired
    @MockBean
    private FamisService famisService;

    Collaborator collaborator = new Collaborator();
    Collaborator savedCollaborator = new Collaborator();
    Collaborator updatedCollaborator = new Collaborator();
    List<Collaborator> collaborators = new ArrayList<>();
    Restaurant restaurant = new Restaurant();
    Address address = new Address();
    DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @BeforeEach
    private void init(){
        standaloneSetup(this.collaboratorController);

        address.setId(UUID.fromString("32b5fd62-2e38-4561-a97d-4ff8fc1da29a"));
        address.setCep("17340170");
        address.setPlace("Rua de lá");
        address.setDistrict("Vila de cá");
        address.setNumber("1111");
        address.setCity("Fim do mundo");
        address.setState("Botas");

        restaurant.setId(UUID.fromString("7602afd1-6de6-4924-ba32-696fa19d886b"));
        restaurant.setName("Restaurante");
        restaurant.setCnpj("145631346");
        restaurant.setPhone("1454878484");
        restaurant.setOpenTime(LocalTime.parse("18:00",hourFormatter));
        restaurant.setCloseTime(LocalTime.parse("24:00",hourFormatter));
        restaurant.setConsumer(20);
        restaurant.setAddress(address);

        collaborator.setId(UUID.fromString("e554cd0f-a050-4fac-8acc-8b33c5a7b00c"));
        collaborator.setName("Felipe");
        collaborator.setLastName("Bonadio");
        collaborator.setCpf("54545454");
        collaborator.setRole("Qualquer");
        collaborator.setPhone("151515151");
        collaborator.setRestaurant(restaurant);
        collaborator.setAddress(address);
        collaborator.setEmail("Email");
        collaborator.setPassword("Password");

        savedCollaborator.setId(UUID.fromString("f562f82d-16b3-4c9f-810e-362fbd4a8f80"));
        savedCollaborator.setName("Felipe");
        savedCollaborator.setLastName("Bonadio");
        savedCollaborator.setCpf("54545454");
        savedCollaborator.setRole("Qualquer");
        savedCollaborator.setPhone("151515151");
        savedCollaborator.setRestaurant(restaurant);
        savedCollaborator.setAddress(address);
        savedCollaborator.setEmail("Email");
        savedCollaborator.setPassword("Password");

        updatedCollaborator.setId(UUID.fromString("e554cd0f-a050-4fac-8acc-8b33c5a7b00c"));
        updatedCollaborator.setName("Guilherme");
        updatedCollaborator.setLastName("Bonadio");
        updatedCollaborator.setCpf("54545454");
        updatedCollaborator.setRole("Qualquer");
        updatedCollaborator.setPhone("151515151");
        updatedCollaborator.setRestaurant(restaurant);
        updatedCollaborator.setAddress(address);
        updatedCollaborator.setEmail("Email");
        updatedCollaborator.setPassword("Password");

        collaborators.add(collaborator);
    }

    @Test
    @DisplayName("Teste para retornar um colaborador por id")
    void getCollaborator() throws Exception {

        when(this.famisService.findCollaboratorById(UUID.fromString("e554cd0f-a050-4fac-8acc-8b33c5a7b00c")))
                .thenReturn(Optional.ofNullable(collaborator));

        given()
                .accept(ContentType.JSON)
        .when()
                .get("collaborators/" + collaborator.getId())
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Teste para retornar um colaborador com NOT FOUND")
    public void getCollaboratorNotFound() {

        when(this.famisService.findCollaboratorById(UUID.fromString("1e72af27-d738-40dc-9b1e-a562f6d5eeab")))
                .thenReturn(null);

        given()
                .accept(ContentType.JSON)
        .when()
                .get("/collaborators/" + collaborator.getId(), UUID.fromString("1e72af27-d738-40dc-9b1e-a562f6d5eeab"))
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Teste para retornar todos os colaboradores.")
    void getCollaborators() {
        when(this.famisService.findAllCollaborators()).thenReturn(collaborators);

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

        given(famisService.saveCollaborator(savedCollaborator)).willReturn(savedCollaborator);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String savedCollaboratorAsJSON = mapper.writeValueAsString(savedCollaborator);
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