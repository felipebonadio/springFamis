package br.com.famis.controller;

import br.com.famis.dto.MesaDto;
import br.com.famis.model.Mesa;
import br.com.famis.service.MesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/mesas")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping("/{mesaId}")
    public ResponseEntity<MesaDto> getMesa(@PathVariable Long mesaId) {
        Optional<MesaDto> mesaEncontrada = mesaService.findMesaById(mesaId);
        return ResponseEntity.ok(mesaEncontrada.get());
    }

    @GetMapping
    public ResponseEntity<List<MesaDto>> getMesas() {
        return ResponseEntity.ok(mesaService.findAllMesas());
    }

    @PostMapping
    public ResponseEntity<MesaDto> criarMesa(@RequestBody MesaDto mesaDto) {
        return new ResponseEntity<>(mesaService.saveMesa(mesaDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MesaDto> updateMesa(@RequestBody MesaDto mesaDto) {
        Optional<MesaDto> mesaParaAtualizar = this.mesaService.updateMesa(mesaDto);
        return ResponseEntity.ok(mesaParaAtualizar.get());

    }

    @DeleteMapping("/{mesaId}")
    public ResponseEntity<Mesa> deleteById(@PathVariable Long mesaId) {
        mesaService.deleteMesa(mesaId);
        return ResponseEntity.noContent().build();
    }
}
