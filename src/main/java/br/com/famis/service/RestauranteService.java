package br.com.famis.service;

import br.com.famis.dto.RestauranteDto;
import br.com.famis.exception.BadRequestException;
import br.com.famis.exception.NotFoundException;
import br.com.famis.mapper.RestauranteMapper;
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

    public Optional<RestauranteDto> findRestauranteById(Long id) {
        Optional<RestauranteDto> restauranteParaProcurar = restauranteRepository.findById(id).map(RestauranteMapper::restauranteToDto);
        if(restauranteParaProcurar.isEmpty()){
            throw new NotFoundException("Não foi possível localizar o restaurante com Id " + id);
        }
        return restauranteParaProcurar;
    }

    public List<RestauranteDto> findAllRestaurantes() throws DataAccessException {
        return restauranteRepository.findAll().stream().map(RestauranteMapper::restauranteToDto).toList();
    }

    public RestauranteDto saveRestaurante(RestauranteDto restauranteDto) throws DataAccessException {
        if(restauranteDto.getNome() == null){
            throw new BadRequestException("Não é possível salvar o restaurante sem nome");
        }
        Restaurante restaurante = RestauranteMapper.dtoToRestaurante(restauranteDto);
        restaurante.setId(null);
        return RestauranteMapper.restauranteToDto(restauranteRepository.save(restaurante));
    }

    public Optional<RestauranteDto> updateRestaurante(RestauranteDto restauranteDto) throws DataAccessException {
        Optional<Restaurante> restauranteParaAtualizar = this.restauranteRepository.findById(restauranteDto.getId());
        if(restauranteParaAtualizar.isEmpty()){
            throw new NotFoundException("Não foiu possível localizar o restaurante com o id " + restauranteDto.getId());
        }
            restauranteParaAtualizar.get().setNome(restauranteDto.getNome());
            restauranteParaAtualizar.get().setTelefone(restauranteDto.getTelefone());
            restauranteParaAtualizar.get().setCnpj(restauranteDto.getCnpj());
            restauranteParaAtualizar.get().setQuantidadeMesa(restauranteDto.getQuantidadeMesa());
            restauranteParaAtualizar.get().setHorarioAbertura(restauranteDto.getHorarioAbertura());
            restauranteParaAtualizar.get().setHorarioEncerramento(restauranteDto.getHorarioEncerramento());
            return Optional.of(restauranteRepository.save(restauranteParaAtualizar.get())).map(RestauranteMapper::restauranteToDto);
    }

    public Optional<RestauranteDto> updateMesaOnRestaurante(RestauranteDto restaurante) throws DataAccessException {
        Optional<Restaurante> restauranteParaAtualizar = this.restauranteRepository.findById(restaurante.getId());
        if(restauranteParaAtualizar.isEmpty()){
            throw new NotFoundException("Não foi possível encontrar o restaurante para atualizar");
        }
            restauranteParaAtualizar.get().setQuantidadeMesa(restaurante.getQuantidadeMesa());
        return Optional.of(restauranteRepository.save(restauranteParaAtualizar.get())).map(RestauranteMapper::restauranteToDto);
    }

    public Optional<RestauranteDto> updateHorarioOnRestaurante(RestauranteDto restaurante) throws DataAccessException {
        Optional<Restaurante> restauranteParaAtualizar = this.restauranteRepository.findById(restaurante.getId());
        if (restauranteParaAtualizar.isEmpty()) {
            throw new NotFoundException("Não foi possível encontrar o restaurante para atualizar");
        }
        restauranteParaAtualizar.get().setHorarioAbertura(restaurante.getHorarioAbertura());
        restauranteParaAtualizar.get().setHorarioEncerramento(restaurante.getHorarioEncerramento());
        return Optional.of(restauranteRepository.save(restauranteParaAtualizar.get())).map(RestauranteMapper::restauranteToDto);
    }

    public void deleteRestaurante(Long id) throws DataAccessException {
        Optional<Restaurante> restauranteParaProcurar = restauranteRepository.findById(id);
        if(restauranteParaProcurar.isEmpty()){
            throw new NotFoundException("Não foi possível encontrar o restaurante para atualizar");
        }
        restauranteRepository.delete(restauranteParaProcurar.get());
    }
}
