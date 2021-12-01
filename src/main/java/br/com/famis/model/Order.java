package br.com.famis.model;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
