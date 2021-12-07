package br.com.famis.repository;

import br.com.famis.model.Consumer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ConsumerRepository extends CrudRepository<Consumer, UUID> {
}
