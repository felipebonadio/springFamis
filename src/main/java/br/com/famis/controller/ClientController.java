package br.com.famis.controller;

import br.com.famis.model.Clients;
import br.com.famis.service.FamisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private FamisService famisService;

    @RequestMapping(value = "/{clientsId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Clients> getClient(@PathVariable("clientsId") UUID clientId){
        Clients clients = this.famisService.findClientById(clientId);
        if(clients == null){
            return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Clients>(clients, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Clients>> getClients(){
        List<Clients> clients = this.famisService.findAllClients();
        if(clients.isEmpty()){
            return new ResponseEntity<List<Clients>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Clients>>(clients, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Clients> saveClient(@RequestBody Clients clients, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (clients == null) || (clients.getName() == null)){
            return new ResponseEntity<Clients>(clients, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Clients>(famisService.saveClient(clients), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Clients> updateClient(@PathVariable("clientId") UUID clientId, @RequestBody Clients clients, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (clients == null) || (clients.getName() == null)){
            return new ResponseEntity<Clients>(clients, HttpStatus.BAD_REQUEST);
        }
        Clients updatedClients = this.famisService.updateClient(clientId, clients);

        if(updatedClients == null){
            return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Clients>(updatedClients, HttpStatus.OK);
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Clients> deleteById(@PathVariable("clientId") UUID clientId) {
        Clients clients = this.famisService.findClientById(clientId);
        famisService.deleteClient(clients);
        if (clients == null) {
            return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Clients>(clients, HttpStatus.OK);
    }
}
