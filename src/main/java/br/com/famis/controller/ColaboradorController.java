package br.com.famis.controller;

import br.com.famis.exception.BadRequestException;
import br.com.famis.exception.NotFoundException;
import br.com.famis.model.Colaborador;
import br.com.famis.service.ColaboradorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/colaboradores")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping("/{colaboradorId}")
    public ResponseEntity<Optional<Colaborador>> getColaborador(@PathVariable Long colaboradorId) throws NotFoundException {
        Optional<Colaborador> colaborador = colaboradorService.findColaboradorById(colaboradorId);
        if(colaborador.isPresent()) {
            return ResponseEntity.ok(colaborador);
        }
        throw new NotFoundException("Colaborador com o ID :"+ colaboradorId+ " não existe!");
    }

    @GetMapping
    public ResponseEntity<List<Colaborador>> getColaboradores() {
        List<Colaborador> colaboradores = this.colaboradorService.findAllColaboradores();
        if (colaboradores.isEmpty()) {
            throw new NotFoundException("Não foi possível encontrar a lista de colaboradores");
        }
        return ResponseEntity.ok(colaboradores);
    }

    @PostMapping
    public ResponseEntity<Colaborador> saveColaborador(@RequestBody Colaborador colaborador) {
        if (colaborador.getNome() == null) {
            throw new BadRequestException("Dados inválidos, por favor confira as informações");
        }
        return new ResponseEntity<>(colaboradorService.saveColaborador(colaborador), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Optional<Colaborador>> updateColaborador(@RequestBody Colaborador colaborador) throws NotFoundException, BadRequestException {
        Optional<Colaborador> updatedColaborador = this.colaboradorService.findColaboradorById(colaborador.getId());
        if(updatedColaborador.isEmpty()){
            throw new NotFoundException("Colaborador com o id :" + colaborador.getId()+ " não foi encontrado!");
        }else if(colaborador.getNome() == null){
            throw new BadRequestException("Dados inválidos, por favor confira!");
        }
        return ResponseEntity.ok(this.colaboradorService.updateColaborador(colaborador));
    }

    @DeleteMapping("/{colaboradorId}")
    public ResponseEntity<Colaborador> deleteById(@PathVariable Long colaboradorId) throws NotFoundException{
        Optional<Colaborador> colaborador = this.colaboradorService.findColaboradorById(colaboradorId);
        if (colaborador.isPresent()) {
            colaboradorService.deleteColaborador(colaborador.get());
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundException("Colaborador com o ID :"+ colaboradorId+ " não existe!");
    }
}
