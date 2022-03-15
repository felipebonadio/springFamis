package br.com.famis.service;

import br.com.famis.dto.ColaboradorDto;
import br.com.famis.exception.BadRequestException;
import br.com.famis.exception.NotFoundException;
import br.com.famis.mapper.ColaboradorMapper;
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

    public ColaboradorService(ColaboradorRepository colaboradorRepository){
        this.colaboradorRepository = colaboradorRepository;
    }

    public Optional<ColaboradorDto> findColaboradorById(Long id){
        Optional<ColaboradorDto> colaboradorParaProcurar = this.colaboradorRepository.findById(id).map(ColaboradorMapper::colaboradorToDto);
        if(colaboradorParaProcurar.isEmpty()){
            throw new NotFoundException("Não foi possível encontrar o colaborador com id "+ id +".");
        }
        return colaboradorParaProcurar;
    }

    public List<ColaboradorDto> findAllColaboradores() {
        return colaboradorRepository.findAll().stream().map(ColaboradorMapper::colaboradorToDto).toList();
    }

    public ColaboradorDto saveColaborador(ColaboradorDto colaboradorDto) {
        if(colaboradorDto.getNome() == null){
            throw new BadRequestException("Não é possível salvar um colaborador sem nome");
        }
        Colaborador colaborador = ColaboradorMapper.dtoToColaborador(colaboradorDto);
        colaborador.setId(null);
        return ColaboradorMapper.colaboradorToDto(colaboradorRepository.save(colaborador));
    }

    public Optional<ColaboradorDto> updateColaborador(ColaboradorDto colaboradorDto) {
        Optional<Colaborador> colaboradorParaAtualizar = this.colaboradorRepository.findById(colaboradorDto.getId());
        if(colaboradorParaAtualizar.isEmpty()){
            throw new BadRequestException("Não foi possível encontrar o colaborador com id "+ colaboradorDto.getId() +".");
        }
            colaboradorParaAtualizar.get().setNome(colaboradorDto.getNome());
            colaboradorParaAtualizar.get().setSobrenome(colaboradorDto.getSobrenome());
            colaboradorParaAtualizar.get().setCpf(colaboradorDto.getCpf());
            colaboradorParaAtualizar.get().setFuncao(colaboradorDto.getFuncao());
            colaboradorParaAtualizar.get().setTelefone(colaboradorDto.getTelefone());
            return Optional.of(colaboradorRepository.save(colaboradorParaAtualizar.get())).map(ColaboradorMapper::colaboradorToDto);

    }

    public void deleteColaborador(Long colaboradorId) {
        Optional<Colaborador> colaboradorDeletar = colaboradorRepository.findById(colaboradorId);
        if(colaboradorDeletar.isEmpty()){
            throw new NotFoundException("Colaborador com o id "+ colaboradorId+ " não existe!");
        }
        colaboradorRepository.delete(colaboradorDeletar.get());
    }

}
