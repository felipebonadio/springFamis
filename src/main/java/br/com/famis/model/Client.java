package br.com.famis.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
public class Client extends Person {

	@OneToMany		
	private List<Address> addresses = new ArrayList<Address>();

	public Client(String name, String lastName, String phone, String cpf, String email, String password, Address address) {
		super(name, lastName, phone, cpf, email, password);
		this.addresses.add(address);
	}

	public Client() {
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
