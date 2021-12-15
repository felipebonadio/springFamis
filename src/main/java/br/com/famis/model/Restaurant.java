package br.com.famis.model;

import lombok.Data;

import javax.persistence.*;
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

    @OneToOne
    private Address address;

    public Restaurant(String name, String phone, String cpnj, Address address) {
        this.name = name;
        this.phone = phone;
        this.cpnj = cpnj;
        this.address = address;
    }

    public Restaurant() {
    }
}
