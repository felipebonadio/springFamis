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

    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final ConsumerRepository consumerRepository;
    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;

    @Autowired
    public FamisServiceImpl(
            AddressRepository addressRepository,
            ClientRepository clientRepository,
            CollaboratorRepository collaboratorRepository,
            ConsumerRepository consumerRepository,
            RestaurantRepository restaurantRepository,
            ProductRepository productRepository
    ){
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.consumerRepository = consumerRepository;
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Address> findAddressById(UUID id) throws DataAccessException {
        return addressRepository.findById(id);
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
    public Optional<Address> updateAddress(Address address) throws DataAccessException {
       Optional<Address> currentAddress = addressRepository.findById(address.getId());
       if (currentAddress.isPresent()){
           currentAddress.get().setCep(address.getCep());
           currentAddress.get().setPlace(address.getPlace());
           currentAddress.get().setNumber(address.getNumber());
           currentAddress.get().setDistrict(address.getDistrict());
           currentAddress.get().setCity(address.getCity());
           currentAddress.get().setState(address.getState());
           return Optional.of(addressRepository.save(currentAddress.get()));
       }
        return Optional.empty();
    }

    @Override
    public void deleteAddress(Address address) throws DataAccessException {
        addressRepository.delete(address);
    }

    @Override
    public Optional<Clients> findClientById(UUID id) throws DataAccessException {
        return clientRepository.findById(id);

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
    public Optional<Clients> updateClient(Clients clients) throws DataAccessException {
        Optional<Clients> currentClients = this.findClientById(clients.getId());
        if(currentClients.isPresent()){
            currentClients.get().setName(clients.getName());
            currentClients.get().setLastName(clients.getLastName());
            currentClients.get().setAddresses(clients.getAddresses());
            currentClients.get().setCpf(clients.getCpf());
            currentClients.get().setPhone(clients.getPhone());
            return Optional.of(clientRepository.save(currentClients.get()));
        }
        return Optional.empty();
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
    public Optional<Collaborator> updateCollaborator(Collaborator collaborator) throws DataAccessException {
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
            return Optional.of(collaboratorRepository.save(currentCollaborator.get()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteCollaborator(Collaborator collaborator) throws DataAccessException {
        collaboratorRepository.delete(collaborator);
    }

    @Override
    public Optional<Consumer> findConsumerById(UUID id) throws DataAccessException {
        return consumerRepository.findById(id);
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
    public Optional<Consumer> updateConsumer(Consumer consumer) throws DataAccessException {
        Optional<Consumer> currentConsumer = consumerRepository.findById(consumer.getId());
        if(currentConsumer.isPresent()){
            currentConsumer.get().setNumber(consumer.getNumber());
            return Optional.of(consumerRepository.save(currentConsumer.get()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteConsumer(Consumer consumer) throws DataAccessException {
        consumerRepository.delete(consumer);
    }

    @Override
    public Optional<Restaurant> findRestaurantById(UUID id) throws DataAccessException {
        return restaurantRepository.findById(id);
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
    public Optional<Restaurant> updateRestaurant(Restaurant restaurant) throws DataAccessException {
        Optional<Restaurant> currentRestaurant = this.findRestaurantById(restaurant.getId());
        if(currentRestaurant.isPresent()){
            currentRestaurant.get().setName(restaurant.getName());
            currentRestaurant.get().setPhone(restaurant.getPhone());
            currentRestaurant.get().setCnpj(restaurant.getCnpj());
            currentRestaurant.get().setAddress(restaurant.getAddress());
            currentRestaurant.get().setConsumer(restaurant.getConsumer());
            currentRestaurant.get().setOpenTime(restaurant.getOpenTime());
            currentRestaurant.get().setCloseTime(restaurant.getCloseTime());
            return Optional.of(restaurantRepository.save(currentRestaurant.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Restaurant> updateConsumerOnRestaurant(Restaurant restaurant) throws DataAccessException {
        Optional<Restaurant> currentRestaurant = this.findRestaurantById(restaurant.getId());
        if(currentRestaurant.isPresent()){
            currentRestaurant.get().setConsumer(restaurant.getConsumer());
            return Optional.of(restaurantRepository.save(currentRestaurant.get()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) throws DataAccessException {
        restaurantRepository.delete(restaurant);
    }

    @Override
    public Optional<Product> findProductById(UUID id) throws DataAccessException {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllProducts() throws DataAccessException {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) throws DataAccessException {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> updateProduct(Product product) throws DataAccessException {
        Optional<Product> currentProduct = productRepository.findById(product.getId());
        if(currentProduct.isPresent()) {
            currentProduct.get().setName(product.getName());
            currentProduct.get().setCategory(product.getCategory());
            currentProduct.get().setValue(product.getValue());
            return Optional.of(productRepository.save(currentProduct.get()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteProduct(Product product) throws DataAccessException {
        productRepository.delete(product);
    }

   
}
