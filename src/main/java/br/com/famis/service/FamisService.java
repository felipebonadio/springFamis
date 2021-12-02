package br.com.famis.service;

import br.com.famis.model.Adress;
import br.com.famis.model.Client;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.UUID;

public interface FamisService {

    Adress findAdressById(UUID id) throws DataAccessException;
    List<Adress> findAllAdresses() throws DataAccessException;
    Adress saveAdress(Adress adress) throws DataAccessException;
    Adress updateAdress(UUID adressId, Adress adress) throws DataAccessException;
    void deleteAdress(Adress adress) throws DataAccessException;

    Client findClientById(UUID id) throws DataAccessException;
    List<Client> findAllClients() throws DataAccessException;
    Client saveClient(Client client) throws DataAccessException;
    Client updateClient(UUID clientId, Client client) throws DataAccessException;
    void deleteClient(Client client) throws DataAccessException;

}
