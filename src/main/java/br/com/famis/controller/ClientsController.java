package br.com.famis.controller;

import br.com.famis.model.Clients;
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
@RequestMapping("/clients")
public class ClientsController {

    private final FamisService famisService;

    public ClientsController(FamisService famisService) {
        this.famisService = famisService;
    }

    @GetMapping("/{clientsId}")
    public ResponseEntity<Clients> getClient(@PathVariable("clientsId") UUID clientId) {
        Optional<Clients> client = this.famisService.findClientById(clientId);
        return client.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Clients>> getClients() {
        List<Clients> clients = this.famisService.findAllClients();
        if (clients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<Clients> saveClient(@RequestBody @Valid Clients clients, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || (clients == null) || (clients.getName() == null)) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(famisService.saveClient(clients), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Clients> updateClient(@RequestBody Clients client, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || (client == null) || (client.getName() == null)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Clients> updatedConsumer = this.famisService.updateClient(client);
        return updatedConsumer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<Clients> deleteById(Clients client) {
        Optional<Clients> deletedClient = this.famisService.findClientById(client.getId());
        if (deletedClient.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        deletedClient.ifPresent(this.famisService::deleteClient);
        return ResponseEntity.ok(deletedClient.get());
    }
}
