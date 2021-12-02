package br.com.famis.service;

import br.com.famis.model.Adress;
import br.com.famis.model.Client;
import br.com.famis.repository.AdressRepository;
import br.com.famis.repository.ClientRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FamisServiceImpl implements FamisService{

    private AdressRepository adressRepository;
    private ClientRepository clientRepository;


    @Override
    public Adress findAdressById(UUID id) throws DataAccessException {
        Optional<Adress> adress = adressRepository.findById(id);
        if (adress.isEmpty()) {
            return null;
        }
        return adress.get();
    }

    @Override
    public List<Adress> findAllAdresses() throws DataAccessException {
        return (List<Adress>) adressRepository.findAll();
    }

    @Override
    public Adress saveAdress(Adress adress) throws DataAccessException {
        return adressRepository.save(adress);
    }

    @Override
    public Adress updateAdress(UUID adressId, Adress adress) throws DataAccessException {
       Adress currentAdress = new Adress();
       if (currentAdress == null){
           return null;
       }
       currentAdress.setCep(adress.getCep());
       currentAdress.setCity(adress.getCity());
       currentAdress.setDistrict(adress.getDistrict());
       currentAdress.setPlace(adress.getPlace());
       currentAdress.setState(adress.getState());
       currentAdress.setCollaborator(adress.getCollaborator());
       currentAdress.setClient(adress.getClient());
       return adressRepository.save(currentAdress);
    }

    @Override
    public void deleteAdress(Adress adress) throws DataAccessException {
        adressRepository.delete(adress);
    }

    @Override
    public Client findClientById(UUID id) throws DataAccessException {
        Optional<Client> client = clientRepository.findById(id);
        if( client.isEmpty()){
            return null;
        }
        return client.get();
    }

    @Override
    public List<Client> findAllClients() throws DataAccessException {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Client saveClient(Client client) throws DataAccessException {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(UUID clientId, Client client) throws DataAccessException {
        Client currentClient = new Client();
        if(currentClient == null){
            return null;
        }
        currentClient.setName(client.getName());
        currentClient.setLastName(client.getLastName());
        currentClient.setAdress(client.getAdress());
        currentClient.setCpf(client.getCpf());
        currentClient.setEmail(client.getEmail());
        currentClient.setPassword(client.getPassword());
        currentClient.setPhone(client.getPhone());
        return currentClient;
    }

    @Override
    public void deleteClient(Client client) throws DataAccessException {
        clientRepository.delete(client);
    }
}
