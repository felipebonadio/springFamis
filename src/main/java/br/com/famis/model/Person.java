package br.com.famis.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
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

	public Person(String name, String lastName, String phone, String cpf) {
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.cpf = cpf;
	}

	public Person() {
	}
}
