package br.com.famis.controller;

import br.com.famis.model.Consumer;
import br.com.famis.service.FamisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/consumers")
public class ConsumerController {

    private FamisService famisService;

    public ConsumerController( FamisService famisService){
        this.famisService = famisService;
    }

    @RequestMapping(value= "/{consumerId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Consumer> getConsumer(@PathVariable("consumerId") UUID consumerId){
        Consumer consumer = this.famisService.findConsumerById(consumerId);
        if (consumer == null){
            return new ResponseEntity<Consumer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Consumer>(consumer, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Consumer>> getConsumers(){
        List<Consumer> consumers = famisService.findAllConsumers();
        if(consumers.isEmpty()){
            return new ResponseEntity<List<Consumer>>( HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<List<Consumer>>(consumers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Consumer> saveConsumer(@RequestBody Consumer consumer, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (consumer == null) || (consumer.getNumber() == null)){
            return new ResponseEntity<Consumer>(consumer, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Consumer>(famisService.saveConsumer(consumer), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/consumerId", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Consumer> updateConsumer(@PathVariable("consumerId") UUID consumerId, @RequestBody Consumer consumer, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (consumer == null) || (consumer.getNumber() == null)){
            return new ResponseEntity<Consumer>(consumer, HttpStatus.BAD_REQUEST);
        }
        Consumer updatedConsumer = this.famisService.updateConsumer(consumerId, consumer);

        if(updatedConsumer == null){
            return new ResponseEntity<Consumer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Consumer>(updatedConsumer, HttpStatus.OK);
    }

    @RequestMapping(value = "/consumerId", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Consumer> deleteById(@PathVariable("consumerId") UUID consumerId) {
        Consumer consumer = this.famisService.findConsumerById(consumerId);
        famisService.deleteConsumer(consumer);
        if (consumer == null) {
            return new ResponseEntity<Consumer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Consumer>(consumer, HttpStatus.OK);
    }
}
