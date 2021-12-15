package br.com.famis.service;

import br.com.famis.model.*;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.UUID;

public interface FamisService {

    Address findAddressById(UUID id) throws DataAccessException;
    List<Address> findAllAddresses() throws DataAccessException;
    Address saveAddress(Address address) throws DataAccessException;
    Address updateAddress(UUID addressId, Address address) throws DataAccessException;
    void deleteAddress(Address address) throws DataAccessException;

    Client findClientById(UUID id) throws DataAccessException;
    List<Client> findAllClients() throws DataAccessException;
    Client saveClient(Client client) throws DataAccessException;
    Client updateClient(UUID clientId, Client client) throws DataAccessException;
    void deleteClient(Client client) throws DataAccessException;

    Collaborator findCollaboratorById(UUID id) throws DataAccessException;
    List<Collaborator> findAllCollaborators() throws DataAccessException;
    Collaborator saveCollaborator(Collaborator collaborator) throws DataAccessException;
    Collaborator updateCollaborator(UUID collaboratorId, Collaborator collaborator) throws DataAccessException;
    void deleteCollaborator(Collaborator collaborator) throws DataAccessException;

    Consumer findConsumerById(UUID id) throws DataAccessException;
    List<Consumer> findAllConsumers() throws DataAccessException;
    Consumer saveConsumer(Consumer consumer) throws DataAccessException;
    Consumer updateConsumer(UUID consumerId, Consumer consumer) throws DataAccessException;
    void deleteConsumer(Consumer consumer) throws DataAccessException;

    Restaurant findRestaurantById(UUID id) throws DataAccessException;
    List<Restaurant> findAllRestaurants() throws DataAccessException;
    Restaurant saveRestaurant(Restaurant restaurant) throws DataAccessException;
    Restaurant updateRestaurant(UUID restaurantId, Restaurant restaurant) throws DataAccessException;
    void deleteRestaurant(Restaurant restaurant) throws DataAccessException;

}
