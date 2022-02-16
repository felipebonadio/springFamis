package br.com.famis.service;

import br.com.famis.model.*;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FamisService {

    Optional<Address> findAddressById(UUID id) throws DataAccessException;
    List<Address> findAllAddresses() throws DataAccessException;
    Address saveAddress(Address address) throws DataAccessException;
    Optional<Address> updateAddress(Address address) throws DataAccessException;
    void deleteAddress(Address address) throws DataAccessException;

    Optional<Collaborator> findCollaboratorById(UUID id) throws DataAccessException;
    List<Collaborator> findAllCollaborators() throws DataAccessException;
    Collaborator saveCollaborator(Collaborator collaborator) throws DataAccessException;
    Optional<Collaborator> updateCollaborator(Collaborator collaborator) throws DataAccessException;
    void deleteCollaborator(Collaborator collaborator) throws DataAccessException;

    Optional<Consumer> findConsumerById(UUID id) throws DataAccessException;
    List<Consumer> findAllConsumers() throws DataAccessException;
    Consumer saveConsumer(Consumer consumer) throws DataAccessException;
    Optional<Consumer> updateConsumer(Consumer consumer) throws DataAccessException;
    void deleteConsumer(Consumer consumer) throws DataAccessException;

    Optional<Restaurant> findRestaurantById(UUID id) throws DataAccessException;
    List<Restaurant> findAllRestaurants() throws DataAccessException;
    Restaurant saveRestaurant(Restaurant restaurant) throws DataAccessException;
    Optional<Restaurant> updateRestaurant(Restaurant restaurant) throws DataAccessException;
    Optional<Restaurant> updateConsumerOnRestaurant(Restaurant restaurant) throws DataAccessException;
    Optional<Restaurant> updateOpenCloseTimeOnRestaurant(Restaurant restaurant) throws DataAccessException;
    void deleteRestaurant(Restaurant restaurant) throws DataAccessException;

    Optional<Product> findProductById(UUID id) throws DataAccessException;
    List<Product> findAllProducts() throws DataAccessException;
    Product saveProduct(Product product) throws DataAccessException;
    Optional<Product> updateProduct(Product product) throws DataAccessException;
    void deleteProduct(Product product) throws DataAccessException;

}
