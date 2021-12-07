package br.com.famis.model;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private LocalDateTime date;

	@Column(nullable = false)
	private Status status;
	
	@Column(nullable = false)
	private BigDecimal discount;
	
	@ManyToOne
    @JoinColumn(name = "collaborator_id")	
	private Collaborator collaborator;		

	@ManyToOne
	@JoinColumn(name = "client_id")		
	private Client client;

	@ManyToOne
	@JoinColumn(name = "consumer_id")
	private Consumer consumer;
}
