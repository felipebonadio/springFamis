package br.com.famis.service;

import br.com.famis.model.Adress;
import org.springframework.dao.DataAccessException;

import java.util.UUID;

public interface FamisService {

    Adress findAdressById(UUID id) throws DataAccessException;


}
