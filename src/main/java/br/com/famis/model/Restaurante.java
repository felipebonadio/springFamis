package br.com.famis.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Data
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
    private LocalTime horarioAbertura;

    @Column
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

    public Restaurante() {
    }
}
