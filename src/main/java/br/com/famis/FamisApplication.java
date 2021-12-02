package br.com.famis;

import br.com.famis.model.Client;
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
}
