package br.com.famis.service;

import br.com.famis.dto.MesaDto;
import br.com.famis.exception.BadRequestException;
import br.com.famis.exception.NotFoundException;
import br.com.famis.mapper.MesaMapper;
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

    public MesaService(MesaRepository mesaRepository){
        this.mesaRepository = mesaRepository;
    }

    public Optional<MesaDto> findMesaById(Long id) throws DataAccessException {
        Optional<MesaDto> mesaParaProcurar = this.mesaRepository.findById(id).map(MesaMapper::mesaToDto);
        if(mesaParaProcurar.isEmpty()){
            throw new NotFoundException("Mesa com o id " + id + " não foi encontrada.");
        }
        return mesaParaProcurar;
    }

    public List<MesaDto> findAllMesas() {
        return mesaRepository.findAll().stream().map(MesaMapper::mesaToDto).toList();
    }

    public MesaDto saveMesa(MesaDto mesaDto){
        if(mesaDto == null){
            throw new BadRequestException("Não foi possível salvar a mesa");
        }
        Mesa mesa = MesaMapper.dtoToMesa(mesaDto);
        mesa.setId(null);
        return MesaMapper.mesaToDto(mesaRepository.save(mesa));
    }

    public Optional<MesaDto> updateMesa(MesaDto mesaDto) throws DataAccessException {
        Optional<Mesa> mesaParaAtualizar = mesaRepository.findById(mesaDto.getId());
        if(mesaDto.getNumero() == null){
            throw new BadRequestException("Não foi possível atualizar o número da mesa");
        }else if (mesaParaAtualizar.isPresent()) {
            mesaParaAtualizar.get().setNumero(mesaParaAtualizar.get().getNumero());
            return Optional.of(mesaRepository.save(mesaParaAtualizar.get())).map(MesaMapper::mesaToDto);
        }
        throw new NotFoundException("Não foi localizar a mesa com o ID " + mesaParaAtualizar.get().getId());
    }

    public void deleteMesa(Long mesaId) throws DataAccessException {
        Optional<Mesa> mesaParaApagar = mesaRepository.findById(mesaId);
        mesaRepository.delete(mesaParaApagar.get());
    }
}
