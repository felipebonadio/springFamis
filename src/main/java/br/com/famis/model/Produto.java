package br.com.famis.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private Double valor;

	@Column(nullable = false)
	private String categoria;

	public Produto(String nome, Double valor, String categoria) {
		this.nome = nome;
		this.valor = valor;
		this.categoria = categoria;
	}

	public Produto() {
	}

	public Produto(Long id) {
		this.id = id;
	}
}
