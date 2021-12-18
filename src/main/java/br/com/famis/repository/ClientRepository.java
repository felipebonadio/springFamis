package br.com.famis.repository;

import br.com.famis.model.Clients;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClientRepository  extends CrudRepository<Clients, UUID> {

}
