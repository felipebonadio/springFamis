package br.com.famis.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.famis.model.Colaborador;

public interface ColaboradorRepository extends CrudRepository<Colaborador, UUID>{

}
