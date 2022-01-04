package br.com.famis;

import br.com.famis.model.*;
import br.com.famis.service.FamisService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class FamisApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamisApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(FamisService famisService) {
		return (args) -> {

			DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");

			Address a1 = new Address("284124", "Rua 1", "500", "Vila", "Barra", "Sp");
			famisService.saveAddress(a1);
			Clients c1 = new Clients("Felipe", "Bonadio", "1444444", "454545454", a1);
			famisService.saveClient(c1);

			Address a2 = new Address("548745", "Rua 5", "698", "Bairro", "Bonita", "Rj");
			famisService.saveAddress(a2);
			Restaurant r1 = new Restaurant("Restaurante", "145556974", "0001254562",20, LocalTime.parse("18:00",hourFormatter), LocalTime.parse("24:00",hourFormatter), a2);
			famisService.saveRestaurant(r1);

			Address a3 = new Address("5656565", "Rua 2"," 708" , "Vila", "Barra", "Sp");
			famisService.saveAddress(a3);
			Collaborator co1 = new Collaborator("Luis", "Fernando", "1444444", "454545454", "CAIXA", "febonadio@teste.com.br", "1244", a3, r1 );
			famisService.saveCollaborator(co1);

			Consumer t1 = new Consumer( 1);
			famisService.saveConsumer(t1);
			};
		}
	}

