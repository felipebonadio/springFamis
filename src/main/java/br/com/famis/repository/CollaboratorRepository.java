package br.com.famis.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.famis.model.Collaborator;

public interface CollaboratorRepository extends CrudRepository<Collaborator, UUID>{

}
