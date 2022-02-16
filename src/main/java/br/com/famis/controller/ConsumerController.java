package br.com.famis.controller;

import br.com.famis.model.Consumer;
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
@RequestMapping("/consumers")
public class ConsumerController {

    private final FamisService famisService;

    public ConsumerController( FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{consumerId}")
    public ResponseEntity<Consumer> getConsumer(@PathVariable("consumerId") UUID consumerId){
        Optional<Consumer> consumer = this.famisService.findConsumerById(consumerId);
        return consumer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Consumer>> getConsumers(){
        List<Consumer> consumers = famisService.findAllConsumers();
        if(consumers.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consumers);
    }

    @PostMapping
    public ResponseEntity<Consumer> saveConsumer(@RequestBody @Valid Consumer consumer, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (consumer == null) || (consumer.getNumber() == null)){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(famisService.saveConsumer(consumer), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (consumer == null) || (consumer.getNumber() == null)){
            return ResponseEntity.badRequest().build();
        }
        Optional<Consumer> updatedConsumer = this.famisService.updateConsumer(consumer);
        return updatedConsumer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{consumerId}")
    public ResponseEntity<Consumer> deleteById(@PathVariable("consumerId") UUID consumerId) {
        Optional<Consumer> consumer = this.famisService.findConsumerById(consumerId);
        if(consumer.isPresent()) {
            famisService.deleteConsumer(consumer.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
