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
    private String name;

    @Column
    private String phone;

    @Column
    private String cnpj;

    @Column
    private Integer consumer;

    @Column
    private LocalTime openTime;

    @Column
    private LocalTime closeTime;

    @OneToOne
    private Endereco endereco;

    public Restaurante(String name, String phone, String cnpj, Integer consumer, LocalTime openTime, LocalTime closeTime, Endereco endereco) {
        this.name = name;
        this.phone = phone;
        this.cnpj = cnpj;
        this.consumer = consumer;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.endereco = endereco;
    }

    public Restaurante() {
    }
}
