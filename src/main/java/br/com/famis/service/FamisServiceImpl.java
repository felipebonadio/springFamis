package br.com.famis.service;

import br.com.famis.model.Address;
import br.com.famis.model.Client;
import br.com.famis.model.Collaborator;
import br.com.famis.repository.AddressRepository;
import br.com.famis.repository.ClientRepository;
import br.com.famis.repository.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FamisServiceImpl implements FamisService{

    private AddressRepository addressRepository;
    private ClientRepository clientRepository;
    private CollaboratorRepository collaboratorRepository;

    @Autowired
    public FamisServiceImpl(
            AddressRepository addressRepository,
            ClientRepository clientRepository,
            CollaboratorRepository collaboratorRepository
    ){
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
        this.collaboratorRepository = collaboratorRepository;
    }

    @Override
    public Address findAddressById(UUID id) throws DataAccessException {
        Optional<Address> adress = addressRepository.findById(id);
        if (adress.isEmpty()) {
            return null;
        }
        return adress.get();
    }

    @Override
    public List<Address> findAllAddresses() throws DataAccessException {
        return (List<Address>) addressRepository.findAll();
    }

    @Override
    public Address saveAddress(Address address) throws DataAccessException {
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(UUID addressId, Address address) throws DataAccessException {
       Address currentAddress = new Address();
       if (currentAddress == null){
           return null;
       }
       currentAddress.setCep(address.getCep());
       currentAddress.setCity(address.getCity());
       currentAddress.setDistrict(address.getDistrict());
       currentAddress.setPlace(address.getPlace());
       currentAddress.setState(address.getState());
//       currentAdress.setCollaborator(adress.getCollaborator());
//       currentAdress.setClient(adress.getClient());
       return addressRepository.save(currentAddress);
    }

    @Override
    public void deleteAddress(Address address) throws DataAccessException {
        addressRepository.delete(address);
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
        currentClient.setAddresses(client.getAddresses());
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

    @Override
    public Collaborator findCollaboratorById(UUID id) throws DataAccessException {
        Optional<Collaborator> collaborator = collaboratorRepository.findById(id);
        if( collaborator.isEmpty()){
            return null;
        }
        return collaborator.get();
    }

    @Override
    public List<Collaborator> findAllCollaborators() throws DataAccessException {
       return (List<Collaborator>) collaboratorRepository.findAll();
    }

    @Override
    public Collaborator saveCollaborator(Collaborator collaborator) throws DataAccessException {
        return collaboratorRepository.save(collaborator);
    }

    @Override
    public Collaborator updateCollaborator(UUID collaboratorId, Collaborator collaborator) throws DataAccessException {
        Collaborator currentCollaborator = new Collaborator();
        if(currentCollaborator == null){
            return null;
        }
        currentCollaborator.setName(collaborator.getName());
        currentCollaborator.setLastName(collaborator.getLastName());
        currentCollaborator.setAddresses(collaborator.getAddresses());
        currentCollaborator.setCpf(collaborator.getCpf());
        currentCollaborator.setEmail(collaborator.getEmail());
        currentCollaborator.setPassword(collaborator.getPassword());
        currentCollaborator.setPhone(collaborator.getPhone());
        return currentCollaborator;
    }

    @Override
    public void deleteCollaborator(Collaborator collaborator) throws DataAccessException {
        collaboratorRepository.delete(collaborator);
    }
}
