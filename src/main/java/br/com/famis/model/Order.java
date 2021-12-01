package br.com.famis.model;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private LocalDateTime date;
	
	@Column(nullable = false)
	private Client client;
	
	@Column(nullable = false)
	private Status status;
	
	@Column(nullable = false)
	private BigDecimal desconto;

	public Order(LocalDateTime date, Client client, Status status, BigDecimal desconto) {
		this.date = date;
		this.client = client;
		this.status = status;
		this.desconto = desconto;
	}

	public Order() {		
	}
	
	
}
