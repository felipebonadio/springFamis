package br.com.famis.controller;

import br.com.famis.model.Mesa;
import br.com.famis.model.Restaurante;
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
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final FamisService famisService;

    public RestauranteController(FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> getRestaurante(@PathVariable("restauranteId") UUID restauranteId) {
        Optional<Restaurante> restaurante = this.famisService.findRestauranteById(restauranteId);
        return restaurante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> getRestaurantes() {
        List<Restaurante> restaurantes = this.famisService.findAllRestaurantes();
        if (restaurantes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurantes);
    }

    @PostMapping
    public ResponseEntity<Restaurante> saveRestaurante(@RequestBody @Valid Restaurante restaurante, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (restaurante == null) || (restaurante.getName() == null)){
            return ResponseEntity.badRequest().build();
        }
        if(restaurante.getEndereco() != null){
            famisService.saveEndereco(restaurante.getEndereco());
        }
        return new ResponseEntity<>(famisService.saveRestaurante(restaurante), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Restaurante> updateRestaurante(@RequestBody Restaurante restaurante, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Optional<Restaurante> updatedRestaurante = this.famisService.updateRestaurante(restaurante);
        return updatedRestaurante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/mesa")
    public ResponseEntity<Restaurante> updateMesaOnRestaurante(@RequestBody Restaurante restaurante, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Optional<Restaurante> updatedRestaurante = this.famisService.updateMesaOnRestaurante(restaurante);
        return updatedRestaurante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/horario")
    public ResponseEntity<Restaurante> updateHorarioOnRestaurante(@RequestBody Restaurante restaurante, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Optional<Restaurante> updatedRestaurant = this.famisService.updateHorarioOnRestaurante(restaurante);
        return updatedRestaurant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<Mesa> deleteById(@PathVariable("restauranteId") UUID restauranteId) {
        Optional<Restaurante> restaurante = this.famisService.findRestauranteById(restauranteId);
        if(restaurante.isPresent()) {
            famisService.deleteRestaurante(restaurante.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

