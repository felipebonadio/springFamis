package br.com.famis.mapper;

import br.com.famis.dto.ColaboradorDto;
import br.com.famis.model.Colaborador;

public class ColaboradorMapper {

    public ColaboradorDto toDto(Colaborador colaborador){
        ColaboradorDto  dto = new ColaboradorDto();
        dto.setNome(colaborador.getNome());
        dto.setSobrenome(colaborador.getSobrenome());
        dto.setFuncao(colaborador.getFuncao());
        return dto;
    }

    public Colaborador toEntity(ColaboradorDto dto){
        Colaborador colaborador = new Colaborador();
        colaborador.setNome(dto.getNome());
        colaborador.setSobrenome(dto.getSobrenome());
        colaborador.setFuncao(dto.getFuncao());
        return colaborador;
    }
}
