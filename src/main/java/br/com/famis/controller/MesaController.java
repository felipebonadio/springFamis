package br.com.famis.controller;

import br.com.famis.model.Mesa;
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
@RequestMapping("/mesas")
public class MesaController {

    private final FamisService famisService;

    public MesaController(FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{mesaId}")
    public ResponseEntity<Mesa> getMesa(@PathVariable("mesaId") UUID mesaId){
        Optional<Mesa> mesa = this.famisService.findMesaById(mesaId);
        return mesa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Mesa>> getMesas(){
        List<Mesa> mesas = famisService.findAllMesas();
        if(mesas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mesas);
    }

    @PostMapping
    public ResponseEntity<Mesa> saveMesa(@RequestBody @Valid Mesa mesa, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (mesa == null) || (mesa.getNumero() == null)){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(famisService.saveMesa(mesa), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Mesa> updateMesa(@RequestBody Mesa mesa, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (mesa == null) || (mesa.getNumero() == null)){
            return ResponseEntity.badRequest().build();
        }
        Optional<Mesa> updatedMesa = this.famisService.updateMesa(mesa);
        return updatedMesa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{mesaId}")
    public ResponseEntity<Mesa> deleteById(@PathVariable("mesaId") UUID mesaId) {
        Optional<Mesa> mesa = this.famisService.findMesaById(mesaId);
        if(mesa.isPresent()) {
            famisService.deleteMesa(mesa.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
