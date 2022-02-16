package br.com.famis.model;

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
	private LocalDateTime date;

	@Column
	private Status status;
	
	@Column
	private Double discount;

	@OneToMany
	private List<Produto> produtos = new ArrayList<>();
	
	@ManyToOne
    @JoinColumn(name = "collaborator_id")	
	private Colaborador colaborador;

	@ManyToOne
	@JoinColumn(name = "consumer_id")
	private Mesa mesa;

	public Pedido(UUID id, LocalDateTime date, Status status, Double discount, Colaborador colaborador, Mesa mesa) {
		this.id = id;
		this.date = date;
		this.status = status;
		this.discount = discount;
		this.colaborador = colaborador;
		this.mesa = mesa;
	}

	public Pedido() {
	}
}
