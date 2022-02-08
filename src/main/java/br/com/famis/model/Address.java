package br.com.famis.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private String cep;
	
	@Column(nullable = false)
	private String place ;

	@Column(nullable = false)
	private String number ;

	@Column(nullable = false)
	private String district;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;	


	public Address(String cep, String place, String number, String district, String city, String state) {
		this.cep = cep;
		this.place = place;
		this.number = number;
		this.district = district;
		this.city = city;
		this.state = state;
	}

	public Address() {
	}
}
