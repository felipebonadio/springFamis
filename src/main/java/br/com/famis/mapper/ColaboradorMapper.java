package br.com.famis.mapper;

import br.com.famis.dto.ColaboradorDto;
import br.com.famis.model.Colaborador;

public class ColaboradorMapper {

    public static ColaboradorDto colaboradorToDto(Colaborador colaborador){
        return ColaboradorDto.builder()
                .id(colaborador.getId())
                .nome(colaborador.getNome())
                .sobrenome(colaborador.getSobrenome())
                .telefone(colaborador.getTelefone())
                .cpf(colaborador.getCpf())
                .funcao(colaborador.getFuncao())
                .build();
    }

    public static Colaborador dtoToColaborador(ColaboradorDto colaboradorDto){
        return Colaborador.builder()
                .id(colaboradorDto.getId())
                .nome(colaboradorDto.getNome())
                .sobrenome(colaboradorDto.getSobrenome())
                .cpf(colaboradorDto.getCpf())
                .telefone(colaboradorDto.getTelefone())
                .funcao(colaboradorDto.getFuncao())
                .build();
    }
}
