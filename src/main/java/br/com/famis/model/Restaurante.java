package br.com.famis.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @Column
    private String telefone;

    @Column
    private String cnpj;

    @Column
    private int quantidadeMesa;

    @Column
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioAbertura;

    @Column
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horarioEncerramento;

    public Restaurante(String nome, String telefone, String cnpj, int quantidadeMesa, LocalTime horarioAbertura,
            LocalTime horarioEncerramento) {
        this.nome = nome;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.quantidadeMesa = quantidadeMesa;
        this.horarioAbertura = horarioAbertura;
        this.horarioEncerramento = horarioEncerramento;
    }

    public Restaurante(Long id) {
        this.id = id;
    }

    public Restaurante() {
    }
}
