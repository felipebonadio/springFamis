package br.com.famis.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class RestauranteDto {
    private Long id;

     private String nome;

    private String telefone;

    private String cnpj;

    private int quantidadeMesa;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalTime horarioAbertura;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalTime horarioEncerramento;
}
