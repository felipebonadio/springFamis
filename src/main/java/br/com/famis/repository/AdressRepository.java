package br.com.famis.repository;

import br.com.famis.model.Adress;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AdressRepository extends CrudRepository<Adress, UUID> {
}
