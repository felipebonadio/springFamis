package br.com.famis.controller;


import br.com.famis.model.Client;
import br.com.famis.model.Collaborator;
import br.com.famis.service.FamisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/collaborators")
public class CollaboratorController {

    @Autowired
    private FamisService famisService;

    @RequestMapping(value = "/{collaboratorId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Collaborator> getCollaborator(@PathVariable("collaboratorId") UUID collaboratorId) {
        Collaborator collaborator = this.famisService.findCollaboratorById(collaboratorId);
        if (collaborator == null) {
            return new ResponseEntity<Collaborator>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collaborator>(collaborator, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Collaborator>> getCollaborators() {
        List<Collaborator> collaborators = this.famisService.findAllCollaborators();
        if (collaborators.isEmpty()) {
            return new ResponseEntity<List<Collaborator>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Collaborator>>(collaborators, HttpStatus.OK);
    }
}
