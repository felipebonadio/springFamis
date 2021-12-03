package br.com.famis;

import br.com.famis.model.Address;
import br.com.famis.model.Client;
import br.com.famis.model.Collaborator;
import br.com.famis.service.FamisService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FamisApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamisApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(FamisService famisService) {
		return (args) -> {

			Address a1 = new Address("284124", "Rua 1", "Vila", "Barra", "Sp");
			famisService.saveAddress(a1);
			Client c1 = new Client("Felipe", "Bonadio", "1444444", "454545454", "febonadio@email", "1234", a1);
			famisService.saveClient(c1);

			Address a2 = new Address("5656565", "Rua 2", "Vila", "Barra", "Sp");
			famisService.saveAddress(a2);
			Collaborator co1 = new Collaborator("Anderson", "Bonadio", "1444444", "454545454", "febonadio@email", "1234", a2);
			famisService.saveCollaborator(co1);


			};
		};

	}

