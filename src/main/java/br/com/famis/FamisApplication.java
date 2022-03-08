package br.com.famis;

import br.com.famis.dto.MesaDto;
import br.com.famis.dto.ProdutoDto;
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

            Restaurante r1 = new Restaurante("Restaurante", "145556974", "0001254562", 20, LocalTime.parse("18:00", formatarHora), LocalTime.parse("24:00", formatarHora));
            restauranteService.saveRestaurante(r1);

            Colaborador colaborador1 = new Colaborador("Luis", "Fernando", "1444444", "454545454", r1, "CAIXA");

            colaboradorService.saveColaborador(colaborador1);
            Colaborador colaborador2 = new Colaborador("Luis", "Fernando", "1444444", "454545454", r1, "CAIXA");
            colaboradorService.saveColaborador(colaborador2);

            ProdutoDto produto1 = new ProdutoDto("Macarrão", 25.00, "Massas");
            produtoService.saveProduto(produto1);

            MesaDto mesa1 = new MesaDto(1, "Luis");
            mesa1.adicionarProduto(produto1);
            mesaService.saveMesa(mesa1);
        };
    }
}
