package br.com.famis.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private BigDecimal value;
	
	@Column(nullable = false)
	private Integer quantity;

	public Products(String name, BigDecimal value, Integer quantity) {
		this.name = name;
		this.value = value;
		this.quantity = quantity;
	}

	public Products() {
	}
	
	
}
