package br.com.famis.dto;

import lombok.Data;

@Data
public class ColaboradorDto {

    private String nome;

    private String sobrenome;

    private String funcao;

    public ColaboradorDto(String nome, String sobrenome, String funcao) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.funcao = funcao;
    }

    public ColaboradorDto() {
    }
}
