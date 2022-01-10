package br.com.famis.service;

import br.com.famis.model.*;
import br.com.famis.repository.*;
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
    private ConsumerRepository consumerRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public FamisServiceImpl(
            AddressRepository addressRepository,
            ClientRepository clientRepository,
            CollaboratorRepository collaboratorRepository,
            ConsumerRepository consumerRepository,
            RestaurantRepository restaurantRepository
    ){
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.consumerRepository = consumerRepository;
        this.restaurantRepository = restaurantRepository;
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
       currentAddress.setPlace(address.getPlace());
       currentAddress.setNumber(address.getNumber());
       currentAddress.setDistrict(address.getDistrict());
       currentAddress.setCity(address.getCity());
       currentAddress.setState(address.getState());
       return addressRepository.save(currentAddress);
    }

    @Override
    public void deleteAddress(Address address) throws DataAccessException {
        addressRepository.delete(address);
    }

    @Override
    public Clients findClientById(UUID id) throws DataAccessException {
        Optional<Clients> client = clientRepository.findById(id);
        if( client.isEmpty()){
            return null;
        }
        return client.get();
    }

    @Override
    public List<Clients> findAllClients() throws DataAccessException {
        return (List<Clients>) clientRepository.findAll();
    }

    @Override
    public Clients saveClient(Clients clients) throws DataAccessException {
        return clientRepository.save(clients);
    }

    @Override
    public Clients updateClient(UUID clientId, Clients clients) throws DataAccessException {
        Clients currentClients = this.findClientById(clientId);
        if(currentClients == null){
            return null;
        }
        currentClients.setName(clients.getName());
        currentClients.setLastName(clients.getLastName());
        currentClients.setAddresses(clients.getAddresses());
        currentClients.setCpf(clients.getCpf());
        currentClients.setPhone(clients.getPhone());
        return clientRepository.save(currentClients);
    }

    @Override
    public void deleteClient(Clients clients) throws DataAccessException {
        clientRepository.delete(clients);
    }

    @Override
    public Optional<Collaborator> findCollaboratorById(UUID id){
      return collaboratorRepository.findById(id);
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
    public Collaborator updateCollaborator(Collaborator collaborator) throws DataAccessException {
        Optional<Collaborator> currentCollaborator = this.findCollaboratorById(collaborator.getId());
        if(currentCollaborator.isPresent()){
                currentCollaborator.get().setName(collaborator.getName());
                currentCollaborator.get().setLastName(collaborator.getLastName());
                currentCollaborator.get().setAddress(collaborator.getAddress());
                currentCollaborator.get().setCpf(collaborator.getCpf());
                currentCollaborator.get().setRole(collaborator.getRole());
                currentCollaborator.get().setEmail(collaborator.getEmail());
                currentCollaborator.get().setPassword(collaborator.getPassword());
                currentCollaborator.get().setPhone(collaborator.getPhone());
                currentCollaborator.get().setRestaurant(collaborator.getRestaurant());
                return collaboratorRepository.save(currentCollaborator.get());
        }
        return null;
    }

    @Override
    public void deleteCollaborator(Collaborator collaborator) throws DataAccessException {
        collaboratorRepository.delete(collaborator);
    }

    @Override
    public Consumer findConsumerById(UUID id) throws DataAccessException {
        Optional<Consumer> consumer = consumerRepository.findById(id);
        if(consumer.isEmpty()){
            return null;
        }
        return consumer.get();
    }

    @Override
    public List<Consumer> findAllConsumers() throws DataAccessException {
        return (List<Consumer>) consumerRepository.findAll();
    }

    @Override
    public Consumer saveConsumer(Consumer consumer) throws DataAccessException {
        return consumerRepository.save(consumer);
    }

    @Override
    public Consumer updateConsumer(UUID consumerId, Consumer consumer) throws DataAccessException {
        Consumer currentConsumer = new Consumer();
        if(currentConsumer == null){
            return null;
        }
        currentConsumer.setNumber(consumer.getNumber());
        return consumerRepository.save(currentConsumer);
    }

    @Override
    public void deleteConsumer(Consumer consumer) throws DataAccessException {
        consumerRepository.delete(consumer);
    }

    @Override
    public Restaurant findRestaurantById(UUID id) throws DataAccessException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isEmpty()) {
            return null;
        }
        return restaurant.get();
    }

    @Override
    public List<Restaurant> findAllRestaurants() throws DataAccessException {
        return (List<Restaurant>)restaurantRepository.findAll();
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) throws DataAccessException {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(UUID restaurantId, Restaurant restaurant) throws DataAccessException {
        Restaurant currentRestaurant = this.findRestaurantById(restaurantId);
        if(currentRestaurant == null){
            return null;
        }
        currentRestaurant.setName(restaurant.getName());
        currentRestaurant.setPhone(restaurant.getPhone());
        currentRestaurant.setCnpj(restaurant.getCnpj());
        currentRestaurant.setAddress(restaurant.getAddress());
        currentRestaurant.setConsumer(restaurant.getConsumer());
        currentRestaurant.setOpenTime(restaurant.getOpenTime());
        currentRestaurant.setCloseTime(restaurant.getCloseTime());

        return restaurantRepository.save(currentRestaurant);
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) throws DataAccessException {
        restaurantRepository.delete(restaurant);
    }
}
