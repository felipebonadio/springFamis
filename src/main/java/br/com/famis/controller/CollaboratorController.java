package br.com.famis.controller;

import br.com.famis.model.Collaborator;
import br.com.famis.service.FamisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/collaborators")
public class CollaboratorController {

    private FamisService famisService;

    public CollaboratorController( FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{collaboratorId}")
    public ResponseEntity<Collaborator> getCollaborator(@PathVariable("collaboratorId") UUID collaboratorId) {
        Collaborator collaborator = this.famisService.findCollaboratorById(collaboratorId);
        if (collaborator == null) {
            return new ResponseEntity<Collaborator>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collaborator>(collaborator, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Collaborator>> getCollaborators() {
        List<Collaborator> collaborators = this.famisService.findAllCollaborators();
        if (collaborators.isEmpty()) {
            return new ResponseEntity<List<Collaborator>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Collaborator>>(collaborators, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Collaborator> saveCollaborator(@RequestBody @Valid Collaborator collaborator, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (collaborator == null) || (collaborator.getName() == null)){
            return new ResponseEntity<Collaborator>(collaborator, HttpStatus.BAD_REQUEST);
        }
        if(collaborator.getAddress() != null){
            famisService.saveAddress(collaborator.getAddress());
        }
        return new ResponseEntity<Collaborator>(famisService.saveCollaborator(collaborator), HttpStatus.CREATED);
    }

    @PutMapping("/{collaboratorId}")
    public ResponseEntity<Collaborator> updateCollaborator(@PathVariable("collaboratorId") UUID collaboratorId, @RequestBody Collaborator collaborator, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (collaborator == null) || (collaborator.getName() == null)){
            return new ResponseEntity<Collaborator>(collaborator, HttpStatus.BAD_REQUEST);
        }
        Collaborator updatedCollaborator = this.famisService.updateCollaborator(collaboratorId, collaborator);
        if(updatedCollaborator == null){
            return new ResponseEntity<Collaborator>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collaborator>(updatedCollaborator, HttpStatus.OK);
    }

    @DeleteMapping("/{collaboratorId}")
    public ResponseEntity<Collaborator> deleteById(@PathVariable("collaboratorId") UUID collaboratorId) {
        Collaborator collaborator = this.famisService.findCollaboratorById(collaboratorId);
        famisService.deleteCollaborator(collaborator);
        if (collaborator == null) {
            return new ResponseEntity<Collaborator>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collaborator>(collaborator, HttpStatus.OK);
    }
}
