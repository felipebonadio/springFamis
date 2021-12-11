package br.com.famis.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Collaborator extends Person {

	@OneToOne
	private Address address;

	public Collaborator(String name, String lastName, String phone, String cpf, String email, String password, Address address) {
		super(name, lastName, phone, cpf, email, password);
		this.address = address;
	}

	public Collaborator() { }


}
