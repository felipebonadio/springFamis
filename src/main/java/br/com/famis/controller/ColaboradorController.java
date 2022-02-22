package br.com.famis.controller;

import br.com.famis.model.Colaborador;
import br.com.famis.service.FamisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/colaboradores")
public class ColaboradorController {

    private final FamisService famisService;

    public ColaboradorController(FamisService famisService) {
        this.famisService = famisService;
    }

    @GetMapping("/{colaboradorId}")
    public ResponseEntity<Colaborador> getColaborador(@PathVariable UUID colaboradorId) {
        Optional<Colaborador> colaborador = famisService.findColaboradorById(colaboradorId);
        return colaborador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Colaborador>> getColaboradores() {
        List<Colaborador> colaboradores = this.famisService.findAllColaboradores();
        if (colaboradores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(colaboradores);
    }

    @PostMapping
    public ResponseEntity<Colaborador> saveColaborador(@RequestBody @Valid Colaborador colaborador,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(famisService.saveColaborador(colaborador), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Colaborador> updateColaborador(@RequestBody Colaborador colaborador,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Colaborador> updatedColaborador = this.famisService.updateColaborador(colaborador);
        return updatedColaborador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{colaboradorId}")
    public ResponseEntity<Colaborador> deleteById(@PathVariable("colaboradorId") UUID colaboradorId) {
        Optional<Colaborador> colaborador = this.famisService.findColaboradorById(colaboradorId);
        if (colaborador.isPresent()) {
            famisService.deleteColaborador(colaborador.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
