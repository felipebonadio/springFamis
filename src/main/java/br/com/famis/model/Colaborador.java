package br.com.famis.model;


import javax.persistence.*;

import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Colaborador {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column
	private String nome;

	@Column
	private String sobrenome;

	@Column
	private String telefone;

	@Column
	private String cpf;

	@OneToOne
	private Endereco endereco;

	@OneToOne
	private Restaurante restaurante;

	@Column
	private String funcao;

	@Column
	private String email;

	@Column
	private String senha;

	public Colaborador(String nome, String sobrenome, String telefone, String cpf, Endereco endereco, Restaurante restaurante, String funcao, String email, String senha) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.cpf = cpf;
		this.endereco = endereco;
		this.restaurante = restaurante;
		this.funcao = funcao;
		this.email = email;
		this.senha = senha;
	}

	public Colaborador() {
	}
}
