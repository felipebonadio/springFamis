package br.com.famis.service;

import br.com.famis.model.Adress;
import br.com.famis.repository.AdressRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FamisServiceImpl implements FamisService{

    private AdressRepository adressRepository;


    @Override
    public Adress findAdressById(UUID id) throws DataAccessException {
        Optional<Adress> adress = adressRepository.findById(id);
        if (adress.isEmpty()) {
            return null;
        }
        return adress.get();
    }

    @Override
    public List<Adress> findAllAdress() throws DataAccessException {
        return (List<Adress>) adressRepository.findAll();
    }

    @Override
    public Adress saveAdress(Adress adress) throws DataAccessException {
        return adressRepository.save(adress);
    }

    @Override
    public Adress updateAdress(UUID adressId, Adress adress) throws DataAccessException {
       Adress currentAdress = new Adress();
       if (currentAdress ==null){
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
}
