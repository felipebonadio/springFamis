package br.com.famis.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

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
}
