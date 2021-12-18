package br.com.famis.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;


@Data
@Entity
public class Clients extends Person {

	@OneToMany		
	private List<Address> addresses = new ArrayList<Address>();

	public Clients(String name, String lastName, String phone, String cpf, Address address) {
		super(name, lastName, phone, cpf);
		this.addresses.add(address);
	}

	public Clients() {
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
