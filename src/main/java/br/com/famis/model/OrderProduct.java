package br.com.famis.model;

import java.util.UUID;

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

public class OrderProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne
    @JoinColumn(name = "products_id")	
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;

	@Column(nullable = false)
	private Integer quantity;

	public OrderProduct(Product product, Orders order, Integer quantity){
		this.product = product;
		this.order = order;
		this.quantity = quantity;
	}

	public OrderProduct(){}
}
