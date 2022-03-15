package br.com.famis.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ColaboradorDto {

    private Long id;

    private String nome;

    private String sobrenome;

    private String telefone;

    private String cpf;

    private String funcao;
}
