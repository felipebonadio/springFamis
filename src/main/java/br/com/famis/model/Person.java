package br.com.famis.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public abstract class Person {

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
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private List<Adress> adress;

	public Person(String name, String lastName, String phone, String cpf, String email, String password,
			List<Adress> adress) {
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		this.adress = adress;
	}

	public Person() {	
	}	
	
	
}
