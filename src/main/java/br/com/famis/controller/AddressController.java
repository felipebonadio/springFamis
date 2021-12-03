package br.com.famis.controller;

import br.com.famis.model.Address;
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
public class AddressController {

    @Autowired
    private FamisService famisService;

    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Address> getAdress(@PathVariable("addressId") UUID addressId){
        Address address = this.famisService.findAddressById(addressId);
        if(address == null){
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Address>> getAddresses(){
        List<Address> addresses = this.famisService.findAllAddresses();
        if(addresses.isEmpty()){
            return new ResponseEntity<List<Address>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (address == null) || (address.getCep() == null)){
            return new ResponseEntity<Address>(address, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Address>(famisService.saveAddress(address), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{adressId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Address> updateAddress(@PathVariable("adressId") UUID adressId, @RequestBody Address address, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (address == null) || (address.getCep() == null)){
            return new ResponseEntity<Address>(address, HttpStatus.BAD_REQUEST);
        }
        Address updatedAddress = this.famisService.updateAddress(adressId, address);

        if(updatedAddress == null){
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Address>(updatedAddress, HttpStatus.OK);
    }

    @RequestMapping(value = "/{addressId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Address> deleteById(@PathVariable("addressId") UUID addressId) {
        Address address = this.famisService.findAddressById(addressId);
        famisService.deleteAddress(address);
        if (address == null) {
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }
}