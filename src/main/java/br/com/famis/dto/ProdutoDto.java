package br.com.famis.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ProdutoDto {

    private Long id;

    private String nome;

    private Double valor;

    private String categoria;

    public ProdutoDto(String nome, Double valor, String categoria) {
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
    }
}
