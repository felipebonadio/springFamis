package br.com.famis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;


@Entity
@Data

public class Orders {

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
	private List<Product> products = new ArrayList<>();
	
	@ManyToOne
    @JoinColumn(name = "collaborator_id")	
	private Collaborator collaborator;		

	@ManyToOne
	@JoinColumn(name = "consumer_id")
	private Consumer consumer;

	public Orders(UUID id, LocalDateTime date, Status status, Double discount, Collaborator collaborator, Consumer consumer) {
		this.id = id;
		this.date = date;
		this.status = status;
		this.discount = discount;
		this.collaborator = collaborator;
		this.consumer = consumer;
	}

	public Orders() {
	}
}
