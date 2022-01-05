package br.com.famis.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Collaborator extends Person {

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

	public Collaborator(String name, String lastName, String phone, String cpf, String role, String email, String password, Address address, Restaurant restaurant) {
		super(name, lastName, phone, cpf);
		this.role = role;
		this.email = email;
		this.password = password;
		this.address = address;
		this.restaurant = restaurant;
	}

	public Collaborator() {
	}
}
