package br.com.famis.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;


@Entity
@Data

public class Collaborator extends Person {

	@OneToMany
	private List<Address> addresses = new ArrayList<Address>();

	public Collaborator(String name, String lastName, String phone, String cpf, String email, String password, Address address) {
		super(name, lastName, phone, cpf, email, password);
		this.addresses.add(address);
	}

	public Collaborator() {
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	public boolean removeAddress(Address address) {
		return this.addresses.remove(address);
	}
}
