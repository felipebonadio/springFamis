package br.com.famis.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;


@Entity
@Data

public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column
	private LocalTime data;

	@Column
	private Status status;
	
	@OneToMany
	private List<Produto> produtos = new ArrayList<>();
	
	@ManyToOne
    @JoinColumn(name = "colaborador_id")
	private Colaborador colaborador;

	@ManyToOne
	@JoinColumn(name = "mesa_id")
	private Mesa mesa;

	public void adicionarProduto(Produto produto){
		this.produtos.add(produto);
	}

	public void removerProduto(Produto produto){
		this.produtos.remove(produto);
	}

	public Pedido(LocalTime data, Status status, Colaborador colaborador, Mesa mesa) {
		this.data = data;
		this.status = status;
		this.colaborador = colaborador;
		this.mesa = mesa;
	}

	public Pedido() {
	}
}
