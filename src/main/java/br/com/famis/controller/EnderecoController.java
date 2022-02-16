package br.com.famis.controller;

import br.com.famis.model.Endereco;
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
@RequestMapping("/enderecos")
public class EnderecoController {

    private final FamisService famisService;

    public EnderecoController(FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{enderecoId}")
    public ResponseEntity<Endereco> getEndereco(@PathVariable("enderecoId") UUID enderecoId){
        Optional<Endereco> endereco = this.famisService.findEnderecoById(enderecoId);
        return endereco.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> getEnderecos(){
        List<Endereco> enderecos = this.famisService.findAllEnderecos();
        if(enderecos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enderecos);
    }

    @PostMapping
    public ResponseEntity<Endereco> saveEndereco(@RequestBody @Valid Endereco endereco, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (endereco == null) || (endereco.getCep() == null)){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(famisService.saveEndereco(endereco), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Endereco> updateEndereco(@RequestBody Endereco endereco, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (endereco == null) || (endereco.getCep() == null)){
            return ResponseEntity.badRequest().build();
        }
        Optional<Endereco> updatedEndereco = this.famisService.updateEndereco(endereco);
        return updatedEndereco.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{enderecoId}")
    public ResponseEntity<Endereco> deleteById(@PathVariable("enderecoId") UUID enderecoId) {
        Optional<Endereco> endereco = this.famisService.findEnderecoById(enderecoId);
        if(endereco.isPresent()) {
            famisService.deleteEndereco(endereco.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}