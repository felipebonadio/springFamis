package br.com.famis.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.famis.model.Product;

public interface ProductRepository extends CrudRepository<Product, UUID>{
    
}
