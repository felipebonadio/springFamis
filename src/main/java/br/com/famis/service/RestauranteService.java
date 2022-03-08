package br.com.famis.service;

import br.com.famis.model.Restaurante;
import br.com.famis.repository.RestauranteRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository){
        this.restauranteRepository = restauranteRepository;
    }

    public Optional<Restaurante> findRestauranteById(Long id) throws DataAccessException {
        return restauranteRepository.findById(id);
    }

    public List<Restaurante> findAllRestaurantes() throws DataAccessException {
        return (List<Restaurante>) restauranteRepository.findAll();
    }

    public Restaurante saveRestaurante(Restaurante restaurante) throws DataAccessException {
        return restauranteRepository.save(restaurante);
    }

    public Optional<Restaurante> updateRestaurante(Restaurante restaurante) throws DataAccessException {
        Optional<Restaurante> currentRestaurant = this.findRestauranteById(restaurante.getId());
        if (currentRestaurant.isPresent()) {
            currentRestaurant.get().setNome(restaurante.getNome());
            currentRestaurant.get().setTelefone(restaurante.getTelefone());
            currentRestaurant.get().setCnpj(restaurante.getCnpj());
            currentRestaurant.get().setQuantidadeMesa(restaurante.getQuantidadeMesa());
            currentRestaurant.get().setHorarioAbertura(restaurante.getHorarioAbertura());
            currentRestaurant.get().setHorarioEncerramento(restaurante.getHorarioEncerramento());
            return Optional.of(restauranteRepository.save(currentRestaurant.get()));
        }
        return Optional.empty();
    }

    public Optional<Restaurante> updateMesaOnRestaurante(Restaurante restaurante) throws DataAccessException {
        Optional<Restaurante> currentRestaurant = this.findRestauranteById(restaurante.getId());
        if (currentRestaurant.isPresent()) {
            currentRestaurant.get().setQuantidadeMesa(restaurante.getQuantidadeMesa());
            return Optional.of(restauranteRepository.save(currentRestaurant.get()));
        }
        return Optional.empty();
    }

    public Optional<Restaurante> updateHorarioOnRestaurante(Restaurante restaurante) throws DataAccessException {
        Optional<Restaurante> currentRestaurant = this.findRestauranteById(restaurante.getId());
        if (currentRestaurant.isPresent()) {
            currentRestaurant.get().setHorarioAbertura(restaurante.getHorarioAbertura());
            currentRestaurant.get().setHorarioEncerramento(restaurante.getHorarioEncerramento());
            return Optional.of(restauranteRepository.save(currentRestaurant.get()));
        }
        return Optional.empty();
    }

    public void deleteRestaurante(Restaurante restaurante) throws DataAccessException {
        restauranteRepository.delete(restaurante);
    }
}
