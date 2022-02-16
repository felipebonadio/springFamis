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

			Endereco a1 = new Endereco("284124", "Rua 1", "500", "Vila", "Barra", "Sp");
			famisService.saveEndereco(a1);

			Endereco a2 = new Endereco("548745", "Rua 5", "698", "Bairro", "Bonita", "Rj");
			famisService.saveEndereco(a2);
			Restaurante r1 = new Restaurante("Restaurante", "145556974", "0001254562",20, LocalTime.parse("18:00",hourFormatter), LocalTime.parse("24:00",hourFormatter), a2);
			famisService.saveRestaurante(r1);

			Endereco a3 = new Endereco("5656565", "Rua 2"," 708" , "Vila", "Barra", "Sp");
			famisService.saveEndereco(a3);
			Colaborador co1 = new Colaborador("Luis", "Fernando", "1444444", "454545454", a1, r1, "CAIXA", "teste@testemail.com.br", "senha");
			famisService.saveColaborador(co1);
			Colaborador co2 = new Colaborador("Luis", "Fernando", "1444444", "454545454", a1, r1, "CAIXA", "teste@testemail.com.br", "senha");
			famisService.saveColaborador(co2);

			Mesa t1 = new Mesa( 1);
			famisService.saveMesa(t1);

			Produto p1 = new Produto("Macarrão", 25.00, "Massas");
			famisService.saveProduto(p1);
			};
		}
	}

