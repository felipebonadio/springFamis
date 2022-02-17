package br.com.famis.controller;

import br.com.famis.model.Pedido;
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
@RequestMapping("/pedidos")
public class PedidoController {

    private final FamisService famisService;

    public PedidoController(FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> getPedido(@PathVariable("pedidoId") UUID pedidoId){
        Optional<Pedido> pedido = this.famisService.findPedidoById(pedidoId);
        return pedido.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos(){
        List<Pedido> pedidos = famisService.findAllPedidos();
        if(pedidos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<Pedido> savePedido(@RequestBody @Valid Pedido pedido, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (pedido == null)){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(famisService.savePedido(pedido), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (pedido == null)){
            return ResponseEntity.badRequest().build();
        }
        Optional<Pedido> updatedPedido = this.famisService.updatePedido(pedido);
        return updatedPedido.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Pedido> deleteById(@PathVariable("pedidoId") UUID pedidoId) {
        Optional<Pedido> pedido = this.famisService.findPedidoById(pedidoId);
        if(pedido.isPresent()) {
            famisService.deletePedido(pedido.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

