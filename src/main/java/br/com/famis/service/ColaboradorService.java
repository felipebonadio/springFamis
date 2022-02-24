package br.com.famis.service;

import br.com.famis.exception.BadRequestException;
import br.com.famis.exception.NotFoundException;
import br.com.famis.model.Colaborador;
import br.com.famis.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    @Autowired
    public ColaboradorService(ColaboradorRepository colaboradorRepository){
        this.colaboradorRepository = colaboradorRepository;
    }

    public Optional<Colaborador> findColaboradorById(UUID id) throws NotFoundException {
        return colaboradorRepository.findById(id);
    }

    public List<Colaborador> findAllColaboradores() throws DataAccessException {
        return (List<Colaborador>) colaboradorRepository.findAll();
    }

    public Colaborador saveColaborador(Colaborador colaborador) throws BadRequestException {
        return colaboradorRepository.save(colaborador);
    }

    public Optional<Colaborador> updateColaborador(Colaborador colaborador) throws DataAccessException {
        Optional<Colaborador> currentCollaborator = this.findColaboradorById(colaborador.getId());
        if (currentCollaborator.isPresent()) {
            currentCollaborator.get().setNome(colaborador.getNome());
            currentCollaborator.get().setSobrenome(colaborador.getSobrenome());
            currentCollaborator.get().setCpf(colaborador.getCpf());
            currentCollaborator.get().setFuncao(colaborador.getFuncao());
            currentCollaborator.get().setTelefone(colaborador.getTelefone());
            currentCollaborator.get().setRestaurante(colaborador.getRestaurante());
            return Optional.of(colaboradorRepository.save(currentCollaborator.get()));
        }else {
            throw new BadRequestException("Erro ao atualizar o colaborador, por favor confira os dados!");
        }
    }

    public void deleteColaborador(Colaborador colaborador) throws DataAccessException {
        Optional<Colaborador> colaboradorDeletar = colaboradorRepository.findById(colaborador.getId());
        if(colaboradorDeletar.isPresent()){
        colaboradorRepository.delete(colaborador);}
        else {
            throw new NotFoundException("Colaborador com o id "+ colaborador.getId()+ " não existe!");
        }
    }

}
