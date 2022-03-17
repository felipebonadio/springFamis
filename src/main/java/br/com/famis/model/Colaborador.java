package br.com.famis.model;

import javax.persistence.*;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class Colaborador {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String nome;

	@Column
	private String sobrenome;

	@Column
	private String telefone;

	@Column
	private String cpf;

	@ManyToOne
	private Restaurante restaurante;

	@Column
	private String funcao;

	public Colaborador(String nome) {
		this.nome = nome;
	}

	public Colaborador(String nome, String sobrenome, String telefone, String cpf, Restaurante restaurante, String funcao) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.cpf = cpf;
		this.restaurante = restaurante;
		this.funcao = funcao;
	}

	public Colaborador() {
	}
}
