package br.com.famis.controller;

import br.com.famis.model.Adress;
import br.com.famis.model.Client;
import br.com.famis.service.FamisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/client")
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
}
