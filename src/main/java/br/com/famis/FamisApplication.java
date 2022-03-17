package br.com.famis;

import br.com.famis.dto.ColaboradorDto;
import br.com.famis.dto.MesaDto;
import br.com.famis.dto.ProdutoDto;
import br.com.famis.dto.RestauranteDto;
import br.com.famis.model.Colaborador;
import br.com.famis.model.Restaurante;
import br.com.famis.service.ColaboradorService;
import br.com.famis.service.MesaService;
import br.com.famis.service.ProdutoService;
import br.com.famis.service.RestauranteService;
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
    public CommandLineRunner demo(ColaboradorService colaboradorService,
                                  MesaService mesaService,
                                  RestauranteService restauranteService,
                                  ProdutoService produtoService) {
        return (args) -> {

            DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm");

            RestauranteDto r1 = new RestauranteDto(1L, "Restaurante", "145556974", "0001254562", 20, LocalTime.parse("18:00", formatarHora), LocalTime.parse("24:00", formatarHora));
            restauranteService.saveRestaurante(r1);
        };
    }
}
