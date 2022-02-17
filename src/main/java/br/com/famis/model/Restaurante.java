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
    private UUID id;

    @Column
    private String nome;

    @Column
    private String telefone;

    @Column
    private String cnpj;

    @Column
    private Integer mesa;

    @Column
    private LocalTime horarioAbertura;

    @Column
    private LocalTime horarioEncerramento;

    @OneToOne
    private Endereco endereco;

    public Restaurante(String nome, String telefone, String cnpj, Integer mesa, Endereco endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.mesa = mesa;       
        this.endereco = endereco;
    }

    public Restaurante(String nome, String telefone, String cnpj, Integer mesa, LocalTime horarioAbertura,
            LocalTime horarioEncerramento, Endereco endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.mesa = mesa;
        this.horarioAbertura = horarioAbertura;
        this.horarioEncerramento = horarioEncerramento;
        this.endereco = endereco;
    }

    public Restaurante() {
    }
}
