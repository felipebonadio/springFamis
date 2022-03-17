package br.com.famis.controller;

import br.com.famis.dto.RestauranteDto;
import br.com.famis.model.Mesa;
import br.com.famis.model.Restaurante;
import br.com.famis.service.RestauranteService;
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

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<RestauranteDto> getRestaurante(@PathVariable Long restauranteId) {
        Optional<RestauranteDto> restaurante = this.restauranteService.findRestauranteById(restauranteId);
        return ResponseEntity.ok(restaurante.get());
    }

    @GetMapping
    public ResponseEntity<List<RestauranteDto>> getRestaurantes() {
        List<RestauranteDto> restaurantes = this.restauranteService.findAllRestaurantes();
        return ResponseEntity.ok(restaurantes);
    }

    @PostMapping
    public ResponseEntity<RestauranteDto> saveRestaurante(@RequestBody @Valid RestauranteDto restauranteDto) {
        return new ResponseEntity<>(restauranteService.saveRestaurante(restauranteDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RestauranteDto> updateRestaurante(@RequestBody RestauranteDto restauranteDto){
        Optional<RestauranteDto> restauranteParaAtualizar = this.restauranteService.updateRestaurante(restauranteDto);
        return ResponseEntity.ok(restauranteParaAtualizar.get());
    }

    @PutMapping("/mesa")
    public ResponseEntity<RestauranteDto> updateMesaOnRestaurante(@RequestBody RestauranteDto restauranteDto) {
        Optional<RestauranteDto> restauranteParaAtualizar = this.restauranteService.updateMesaOnRestaurante(restauranteDto);
        return ResponseEntity.ok(restauranteParaAtualizar.get());
    }

    @PutMapping("/horario")
    public ResponseEntity<RestauranteDto> updateHorarioOnRestaurante(@RequestBody RestauranteDto restauranteDto) {
        Optional<RestauranteDto> restauranteParaAtualizar = this.restauranteService.updateHorarioOnRestaurante(restauranteDto);
        return ResponseEntity.ok(restauranteParaAtualizar.get());
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<Mesa> deleteById(@PathVariable Long restauranteId) {
        restauranteService.deleteRestaurante(restauranteId);
        return ResponseEntity.noContent().build();
    }
}
