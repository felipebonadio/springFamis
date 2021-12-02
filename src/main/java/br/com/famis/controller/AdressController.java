package br.com.famis.controller;

import br.com.famis.model.Adress;
import br.com.famis.service.FamisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/adresses")
public class AdressController {

    @Autowired
    private FamisService famisService;

    @RequestMapping(value = "/id/{adressId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Adress> getAdress(@PathVariable("adressId") UUID adressId){
        Adress adress = this.famisService.findAdressById(adressId);
        if(adress == null){
            return new ResponseEntity<Adress>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Adress>(adress, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Adress>> getAdresses(){
        List<Adress> adresses = this.famisService.findAllAdresses();
        if(adresses.isEmpty()){
            return new ResponseEntity<List<Adress>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Adress>>(adresses, HttpStatus.OK);
    }
}
