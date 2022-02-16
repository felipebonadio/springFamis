package br.com.famis.repository;

import br.com.famis.model.Mesa;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MesaRepository extends CrudRepository<Mesa, UUID> {
}
