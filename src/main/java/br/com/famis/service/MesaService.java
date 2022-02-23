package br.com.famis.service;

import br.com.famis.model.Mesa;
import br.com.famis.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    @Autowired
    public MesaService(MesaRepository mesaRepository){
        this.mesaRepository = mesaRepository;
    }

    public Optional<Mesa> findMesaById(UUID id) throws DataAccessException {
        return mesaRepository.findById(id);
    }

    public List<Mesa> findAllMesas() throws DataAccessException {
        return (List<Mesa>) mesaRepository.findAll();
    }

    public Mesa saveMesa(Mesa mesa) throws DataAccessException {
        return mesaRepository.save(mesa);
    }

    public Optional<Mesa> updateMesa(Mesa mesa) throws DataAccessException {
        Optional<Mesa> currentConsumer = mesaRepository.findById(mesa.getId());
        if (currentConsumer.isPresent()) {
            currentConsumer.get().setNumero(mesa.getNumero());
            return Optional.of(mesaRepository.save(currentConsumer.get()));
        }
        return Optional.empty();
    }

    public void deleteMesa(Mesa mesa) throws DataAccessException {
        mesaRepository.delete(mesa);
    }
}
