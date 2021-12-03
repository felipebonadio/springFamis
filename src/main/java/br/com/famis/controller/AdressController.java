package br.com.famis.controller;

import br.com.famis.model.Adress;
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
@RequestMapping("/adresses")
public class AdressController {

    @Autowired
    private FamisService famisService;

    @RequestMapping(value = "/{adressId}", method = RequestMethod.GET, produces = "application/json")
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

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Adress> saveAdress(@RequestBody Adress adress, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (adress == null) || (adress.getCep() == null)){
            return new ResponseEntity<Adress>(adress, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Adress>(famisService.saveAdress(adress), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{adressId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Adress> updateAdress(@PathVariable("adressId") UUID adressId, @RequestBody Adress adress, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (adress == null) || (adress.getCep() == null)){
            return new ResponseEntity<Adress>(adress, HttpStatus.BAD_REQUEST);
        }
        Adress updatedAdress = this.famisService.updateAdress(adressId, adress);

        if(updatedAdress == null){
            return new ResponseEntity<Adress>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Adress>(updatedAdress, HttpStatus.OK);
    }

    @RequestMapping(value = "/{adressId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Adress> deleteById(@PathVariable("adressId") UUID adressId) {
        Adress adress = this.famisService.findAdressById(adressId);
        famisService.deleteAdress(adress);
        if (adress == null) {
            return new ResponseEntity<Adress>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Adress>(adress, HttpStatus.OK);
    }
}