package br.com.famis.controller;

import br.com.famis.exceptions.IdNotFoundException;
import br.com.famis.model.Collaborator;
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
    public ResponseEntity<Collaborator> getCollaborator(@PathVariable UUID collaboratorId) throws IdNotFoundException {
        Optional<Collaborator> collaborator = famisService.findCollaboratorById(collaboratorId);
        if (collaborator.isPresent()) {
            return new ResponseEntity<>(collaborator.get(), HttpStatus.OK);
        }
        throw new IdNotFoundException("Id não encontrada");
    }

    @GetMapping
    public ResponseEntity<List<Collaborator>> getCollaborators() {
        List<Collaborator> collaborators = this.famisService.findAllCollaborators();
        if (collaborators.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(collaborators, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Collaborator> saveCollaborator(@RequestBody @Valid Collaborator collaborator, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(collaborator, HttpStatus.BAD_REQUEST);
        }
        if(collaborator.getAddress() != null){
            famisService.saveAddress(collaborator.getAddress());
        }
        return new ResponseEntity<>(famisService.saveCollaborator(collaborator), HttpStatus.CREATED);
    }

    @PutMapping("/{collaboratorId}")
    public ResponseEntity<Collaborator> updateCollaborator(@PathVariable UUID collaboratorId, @RequestBody Collaborator collaborator, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (collaborator == null) || (collaborator.getName() == null)){
            return new ResponseEntity<>(collaborator, HttpStatus.BAD_REQUEST);
        }
        Collaborator updatedCollaborator = this.famisService.updateCollaborator(collaboratorId, collaborator);
        if(updatedCollaborator == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCollaborator, HttpStatus.OK);
    }

    @DeleteMapping("/{collaboratorId}")
    public ResponseEntity<Collaborator> deleteById(@PathVariable UUID collaboratorId) {
        Optional<Collaborator> collaborator = this.famisService.findCollaboratorById(collaboratorId);
        if (collaborator.isPresent()) {
            famisService.deleteCollaborator(collaborator.get());
            return new ResponseEntity<>(collaborator.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
