package br.com.famis.service;

import br.com.famis.model.Adress;
import br.com.famis.repository.AdressRepository;
import org.springframework.dao.DataAccessException;

import java.util.Optional;
import java.util.UUID;

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
}
