package br.com.famis.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Collaborator extends Person {

	@OneToOne
	private Address address;

	@OneToOne
	private Restaurant restaurant;

	@Column(nullable = false)
	private String role;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
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
