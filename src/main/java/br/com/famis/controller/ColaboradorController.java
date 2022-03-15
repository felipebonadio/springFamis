package br.com.famis.controller;

import br.com.famis.dto.ColaboradorDto;
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
    public ResponseEntity<ColaboradorDto> getColaborador(@PathVariable Long colaboradorId) throws NotFoundException {
        Optional<ColaboradorDto> colaborador = colaboradorService.findColaboradorById(colaboradorId);
        return ResponseEntity.ok(colaborador.get());
    }

    @GetMapping
    public ResponseEntity<List<ColaboradorDto>> getColaboradores() {
        List<ColaboradorDto> colaboradores = this.colaboradorService.findAllColaboradores();
        return ResponseEntity.ok(colaboradores);
    }

    @PostMapping
    public ResponseEntity<ColaboradorDto> saveColaborador(@RequestBody ColaboradorDto colaboradorDto) {
        return new ResponseEntity<>(colaboradorService.saveColaborador(colaboradorDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ColaboradorDto> updateColaborador(@RequestBody ColaboradorDto colaboradorDto) {
        Optional<ColaboradorDto> colaboradorParaAtualizar = this.colaboradorService.updateColaborador(colaboradorDto);
        return ResponseEntity.ok(colaboradorParaAtualizar.get());
    }

    @DeleteMapping("/{colaboradorId}")
    public ResponseEntity<Colaborador> deleteById(@PathVariable Long colaboradorId) throws NotFoundException{
        colaboradorService.deleteColaborador(colaboradorId);
        return ResponseEntity.noContent().build();
    }
}
