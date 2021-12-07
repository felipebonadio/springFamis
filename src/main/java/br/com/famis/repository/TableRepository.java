package br.com.famis.repository;

import br.com.famis.model.Table;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TableRepository extends CrudRepository<Table, UUID> {
}
