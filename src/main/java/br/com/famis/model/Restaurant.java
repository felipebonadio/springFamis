package br.com.famis.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String cpnj;

    @Column
    private String consumer;

    @Column
    private LocalTime openTime;

    @Column
    private LocalTime closeTime;

    @OneToOne
    private Address address;

    public Restaurant(String name, String phone, String cpnj, String consumer, LocalTime openTime, LocalTime closeTime, Address address) {
        this.name = name;
        this.phone = phone;
        this.cpnj = cpnj;
        this.consumer = consumer;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.address = address;
    }

    public Restaurant() {
    }
}
