package br.com.famis.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Collaborator{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String cpf;

	@NotNull
	@OneToOne
	private Address address;

	@NotNull
	@OneToOne
	private Restaurant restaurant;

	@NotNull
	@Column
	private String role;

	@NotNull
	@Column
	private String email;

	@NotNull
	@Column
	private String password;

	public Collaborator(String name, String lastName, String phone, String cpf, Address address, Restaurant restaurant, String role, String email, String password) {
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.cpf = cpf;
		this.address = address;
		this.restaurant = restaurant;
		this.role = role;
		this.email = email;
		this.password = password;
	}

	public Collaborator() {
	}
}
