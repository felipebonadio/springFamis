package br.com.famis.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Colaborador {

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

	@NotNull
	@OneToOne
	private Endereco endereco;

	@NotNull
	@OneToOne
	private Restaurante restaurante;

	@NotNull
	@Column
	private String role;

	@NotNull
	@Column
	private String email;

	@NotNull
	@Column
	private String password;

	public Colaborador(String name, String lastName, String phone, String cpf, Endereco endereco, Restaurante restaurante, String role, String email, String password) {
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.cpf = cpf;
		this.endereco = endereco;
		this.restaurante = restaurante;
		this.role = role;
		this.email = email;
		this.password = password;
	}

	public Colaborador() {
	}
}
