package br.com.famis.mapper;

import br.com.famis.dto.RestauranteDto;
import br.com.famis.model.Restaurante;

public class RestauranteMapper {

    public static RestauranteDto restauranteToDto(Restaurante restaurante){
        return RestauranteDto.builder()
                .id(restaurante.getId())
                .nome(restaurante.getNome())
                .cnpj(restaurante.getCnpj())
                .quantidadeMesa(restaurante.getQuantidadeMesa())
                .telefone(restaurante.getTelefone())
                .horarioAbertura(restaurante.getHorarioAbertura())
                .horarioEncerramento(restaurante.getHorarioEncerramento())
                .build();
    }

    public static Restaurante dtoToRestaurante(RestauranteDto restauranteDto){
        return Restaurante.builder()
                .id(restauranteDto.getId())
                .nome(restauranteDto.getNome())
                .cnpj(restauranteDto.getCnpj())
                .quantidadeMesa(restauranteDto.getQuantidadeMesa())
                .telefone(restauranteDto.getTelefone())
                .horarioAbertura(restauranteDto.getHorarioAbertura())
                .horarioEncerramento(restauranteDto.getHorarioEncerramento())
                .build();
    }
}
