package br.com.famis.controller;

import br.com.famis.model.Client;
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

    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Client> getClient(@PathVariable("clientId") UUID clientId){
        Client client = this.famisService.findClientById(clientId);
        if(client == null){
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Client>> getClients(){
        List<Client> clients = this.famisService.findAllClients();
        if(clients.isEmpty()){
            return new ResponseEntity<List<Client>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Client> saveClient(@RequestBody Client client, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (client == null) || (client.getName() == null)){
            return new ResponseEntity<Client>(client, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Client>(famisService.saveClient(client), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Client> updateClient(@PathVariable("clientId") UUID clientId, @RequestBody Client client, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (client == null) || (client.getName() == null)){
            return new ResponseEntity<Client>(client, HttpStatus.BAD_REQUEST);
        }
        Client updatedClient = this.famisService.updateClient(clientId, client);

        if(updatedClient == null){
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Client>(updatedClient, HttpStatus.OK);
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Client> deleteById(@PathVariable("clientId") UUID clientId) {
        Client client = this.famisService.findClientById(clientId);
        famisService.deleteClient(client);
        if (client == null) {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }
}
