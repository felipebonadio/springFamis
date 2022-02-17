package br.com.famis.service;

import br.com.famis.dto.request.MesaRequest;
import br.com.famis.model.*;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FamisService {

    Optional<Endereco> findEnderecoById(UUID id) throws DataAccessException;
    List<Endereco> findAllEnderecos() throws DataAccessException;
    Endereco saveEndereco(Endereco endereco) throws DataAccessException;
    Optional<Endereco> updateEndereco(Endereco endereco) throws DataAccessException;
    void deleteEndereco(Endereco endereco) throws DataAccessException;

    Optional<Colaborador> findColaboradorById(UUID id) throws DataAccessException;
    List<Colaborador> findAllColaboradores() throws DataAccessException;
    Colaborador saveColaborador(Colaborador colaborador) throws DataAccessException;
    Optional<Colaborador> updateColaborador(Colaborador colaborador) throws DataAccessException;
    void deleteColaborador(Colaborador colaborador) throws DataAccessException;

    Optional<Mesa> findMesaById(UUID id) throws DataAccessException;
    List<Mesa> findAllMesas() throws DataAccessException;
    Mesa saveMesa(Mesa mesa) throws DataAccessException;
    Optional<Mesa> updateMesa(Mesa mesa) throws DataAccessException;
    void deleteMesa(Mesa mesa) throws DataAccessException;

    Optional<Restaurante> findRestauranteById(UUID id) throws DataAccessException;
    List<Restaurante> findAllRestaurantes() throws DataAccessException;
    Restaurante saveRestaurante(Restaurante restaurante) throws DataAccessException;
    Optional<Restaurante> updateRestaurante(Restaurante restaurante) throws DataAccessException;
    Optional<Restaurante> updateMesaOnRestaurante(Restaurante restaurante) throws DataAccessException;
    Optional<Restaurante> updateHorarioOnRestaurante(Restaurante restaurante) throws DataAccessException;
    void deleteRestaurante(Restaurante restaurante) throws DataAccessException;

    Optional<Produto> findProdutoById(UUID id) throws DataAccessException;
    List<Produto> findAllProduto() throws DataAccessException;
    Produto saveProduto(Produto produto) throws DataAccessException;
    Optional<Produto> updateProduto(Produto produto) throws DataAccessException;
    void deleteProduto(Produto produto) throws DataAccessException;   
}
