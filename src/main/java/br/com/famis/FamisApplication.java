package br.com.famis;

import br.com.famis.model.*;
import br.com.famis.service.FamisService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class FamisApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamisApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(FamisService famisService) {
		return (args) -> {

			DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm");

			Endereco endereco1 = new Endereco("284124", "Rua 1", "500", "Vila", "Barra", "Sp");
			famisService.saveEndereco(endereco1);

			Endereco endereco2 = new Endereco("548745", "Rua 5", "698", "Bairro", "Bonita", "Rj");
			famisService.saveEndereco(endereco2);
			Restaurante r1 = new Restaurante("Restaurante", "145556974", "0001254562",20, LocalTime.parse("18:00",formatarHora), LocalTime.parse("24:00",formatarHora), endereco1);
			famisService.saveRestaurante(r1);

			Endereco endereco3 = new Endereco("5656565", "Rua 2"," 708" , "Vila", "Barra", "Sp");
			famisService.saveEndereco(endereco3);
			Colaborador colaborador1 = new Colaborador("Luis", "Fernando", "1444444", "454545454", endereco2, r1, "CAIXA", "teste@testemail.com.br", "senha");
			famisService.saveColaborador(colaborador1);
			Colaborador colaborador2 = new Colaborador("Luis", "Fernando", "1444444", "454545454", endereco3, r1, "CAIXA", "teste@testemail.com.br", "senha");
			famisService.saveColaborador(colaborador2);

			Mesa mesa1 = new Mesa( 1);
			famisService.saveMesa(mesa1);

			Produto produto1 = new Produto("Macarrão", 25.00, "Massas");
			famisService.saveProduto(produto1);

			Pedido pedido1 = new Pedido(LocalTime.parse("18:00",formatarHora), Status.PREPARANDO, colaborador1, mesa1);
			pedido1.adicionarProduto(produto1);
			famisService.savePedido(pedido1);
			};
		}
	}

