package br.com.famis.repository;

import br.com.famis.model.Restaurante;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RestauranteRepository extends CrudRepository<Restaurante, UUID> {
}
