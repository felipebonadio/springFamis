package br.com.famis.controller;

import br.com.famis.model.Collaborator;
import br.com.famis.model.Consumer;
import br.com.famis.model.Restaurant;
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
@RequestMapping("/collaborators")
public class CollaboratorController {

    private final FamisService famisService;

    public CollaboratorController(FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{collaboratorId}")
    public ResponseEntity<Collaborator> getCollaborator(@PathVariable UUID collaboratorId) {
        Optional<Collaborator> collaborator = famisService.findCollaboratorById(collaboratorId);
        return collaborator.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Collaborator>> getCollaborators() {
        List<Collaborator> collaborators = this.famisService.findAllCollaborators();
        if (collaborators.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(collaborators);
    }

    @PostMapping
    public ResponseEntity<Collaborator> saveCollaborator(@RequestBody @Valid Collaborator collaborator, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        if(collaborator.getAddress() != null){
            famisService.saveAddress(collaborator.getAddress());
        }
        return new ResponseEntity<>(famisService.saveCollaborator(collaborator), HttpStatus.CREATED);
    }

    @PutMapping("/{collaboratorId}")
    public ResponseEntity<Collaborator> updateCollaborator(@RequestBody Collaborator collaborator, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Optional<Collaborator> updatedCollaborator = this.famisService.updateCollaborator(collaborator);
        return updatedCollaborator.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{collaboratorId}")
    public ResponseEntity<Collaborator> deleteById(@PathVariable("collaboratorId") UUID collaboratorId) {
        Optional<Collaborator> collaborator = this.famisService.findCollaboratorById(collaboratorId);
        if(collaborator.isPresent()) {
            famisService.deleteCollaborator(collaborator.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
