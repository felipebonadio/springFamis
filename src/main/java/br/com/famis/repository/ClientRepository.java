package br.com.famis.repository;

import br.com.famis.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClientRepository  extends CrudRepository<Client, UUID> {

}
