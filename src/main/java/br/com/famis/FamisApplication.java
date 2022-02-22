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

			DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm");

			Restaurante r1 = new Restaurante("Restaurante", "145556974", "0001254562", 20,
					LocalTime.parse("18:00", formatarHora), LocalTime.parse("24:00", formatarHora));
			famisService.saveRestaurante(r1);

			Colaborador colaborador1 = new Colaborador("Luis", "Fernando", "1444444", "454545454", r1,
					"CAIXA");
			famisService.saveColaborador(colaborador1);
			Colaborador colaborador2 = new Colaborador("Luis", "Fernando", "1444444", "454545454", r1,
					"CAIXA");
			famisService.saveColaborador(colaborador2);

			Produto produto1 = new Produto("Macarrão", 25.00, "Massas");
			famisService.saveProduto(produto1);

			Mesa mesa1 = new Mesa(1, colaborador1);
			mesa1.adicionarProduto(produto1);
			famisService.saveMesa(mesa1);
		};
	}
}
