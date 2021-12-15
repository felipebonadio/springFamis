package br.com.famis.repository;

import br.com.famis.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RestaurantRepository extends CrudRepository<Restaurant, UUID> {
}
