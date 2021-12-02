package br.com.famis.service;

import br.com.famis.model.Adress;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.UUID;

public interface FamisService {

    Adress findAdressById(UUID id) throws DataAccessException;
    List<Adress> findAllAdress() throws DataAccessException;
    Adress saveAdress(Adress adress) throws DataAccessException;
    Adress updateAdress(UUID adressId, Adress adress) throws DataAccessException;
    void deleteAdress(Adress adress) throws DataAccessException;

}
