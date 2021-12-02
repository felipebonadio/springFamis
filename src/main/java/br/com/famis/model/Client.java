package br.com.famis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Client extends Person {

	@OneToMany		
	private List<Adress> adress = new ArrayList<Adress>();

	public Client(String name, String lastName, String phone, String cpf, String email, String password, Adress adress) {
		super(name, lastName, phone, cpf, email, password);
		this.adress.add(adress);
	}

	public Client() {
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

	public boolean removeReserve(Adress adress) {
		return this.adress.remove(adress);
	}
}
