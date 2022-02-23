package br.com.famis.controller;

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
    public ResponseEntity<Colaborador> getColaborador(@PathVariable UUID colaboradorId) {
        Optional<Colaborador> colaborador = colaboradorService.findColaboradorById(colaboradorId);
        return colaborador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Colaborador>> getColaboradores() {
        List<Colaborador> colaboradores = this.colaboradorService.findAllColaboradores();
        if (colaboradores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(colaboradores);
    }

    @PostMapping
    public ResponseEntity<Colaborador> saveColaborador(@RequestBody Colaborador colaborador) {
        if (colaborador.getNome() == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(colaboradorService.saveColaborador(colaborador), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Colaborador> updateColaborador(@RequestBody Colaborador colaborador,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Colaborador> updatedColaborador = this.colaboradorService.updateColaborador(colaborador);
        return updatedColaborador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{colaboradorId}")
    public ResponseEntity<Colaborador> deleteById(@PathVariable UUID colaboradorId) {
        Optional<Colaborador> colaborador = this.colaboradorService.findColaboradorById(colaboradorId);
        if (colaborador.isPresent()) {
            colaboradorService.deleteColaborador(colaborador.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
