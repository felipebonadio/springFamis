package br.com.famis.model;

import java.util.UUID;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;


@Entity
@Data

public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private LocalDateTime date;

	@Column(nullable = false)
	private Status status;
	
	@Column(nullable = false)
	private Double discount;
	
	@ManyToOne
    @JoinColumn(name = "collaborator_id")	
	private Collaborator collaborator;		

	@ManyToOne
	@JoinColumn(name = "clients_id")
	private Clients client;

	@ManyToOne
	@JoinColumn(name = "consumer_id")
	private Consumer consumer;

	public Orders(LocalDateTime date, Status status, Double discount, Collaborator collaborator, Clients client, Consumer consumer){
		this.date = date;
		this.status = status;
		this.discount = discount;
		this.collaborator = collaborator;
		this.client = client;
		this.consumer = consumer;
	}

	public Orders(){

	}
}
