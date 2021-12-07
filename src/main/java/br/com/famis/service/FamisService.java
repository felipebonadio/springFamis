package br.com.famis.service;

import br.com.famis.model.Address;
import br.com.famis.model.Client;
import br.com.famis.model.Collaborator;
import br.com.famis.model.Table;
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

    Table findTableById(UUID id) throws DataAccessException;
    List<Table> findAllTables() throws DataAccessException;
    Table saveTable(Table table) throws DataAccessException;
    Table updateTable(UUID tableId, Table table) throws DataAccessException;
    void deleteTable(Table table) throws DataAccessException;

}
