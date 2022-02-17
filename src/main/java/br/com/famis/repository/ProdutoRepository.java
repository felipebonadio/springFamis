package br.com.famis.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.famis.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, UUID>{
    
}