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
	private List<Adress> adress = new ArrayList<Adress>();

	public Collaborator(String name, String lastName, String phone, String cpf, String email, String password, Adress adress) {
		super(name, lastName, phone, cpf, email, password);
		this.adress.add(adress);
	}

	public Collaborator() {
	}

	public List<Adress> getAdress() {
		return adress;
	}

	public void setAdress(List<Adress> adress) {
		this.adress = adress;
	}

	public void addAdress(Adress adress) {
		this.adress.add(adress);
	}

	public boolean removeAdress(Adress adress) {
		return this.adress.remove(adress);
	}
}
