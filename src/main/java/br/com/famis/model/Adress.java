package br.com.famis.model;

import java.util.UUID;

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

@Data
@Entity
public class Adress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private String cep;
	
	@Column(nullable = false)
	private String place ;
	
	@Column(nullable = false)
	private String district;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;	
	
//	@ManyToOne
//	@JoinColumn(name = "client_id")
//	private Client client;
//
//	@ManyToOne
//	@JoinColumn(name = "collaborator_id")
//	private Collaborator collaborator;


	public Adress(String cep, String place, String district, String city, String state) {
		this.cep = cep;
		this.place = place;
		this.district = district;
		this.city = city;
		this.state = state;
	}

	public Adress() {
	}
}
