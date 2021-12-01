package br.com.famis.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class OrderProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private List<Products> products;
	
	@Column(nullable = false)
	private Order order;

	public OrderProduct(List<Products> products, Order order) {		
		this.products = products;
		this.order = order;
	}

	public OrderProduct() {		
	}
}
