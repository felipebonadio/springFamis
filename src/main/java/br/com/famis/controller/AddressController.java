package br.com.famis.controller;

import br.com.famis.model.Address;
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
@RequestMapping("/addresses")
public class AddressController {

    private final FamisService famisService;

    public AddressController (FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAdress(@PathVariable("addressId") UUID addressId){
        Address address = this.famisService.findAddressById(addressId);
        if(address == null){
           return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAddresses(){
        List<Address> addresses = this.famisService.findAllAddresses();
        if(addresses.isEmpty()){
            return new ResponseEntity<List<Address>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Address> saveAddress(@RequestBody @Valid  Address address, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (address == null) || (address.getCep() == null)){
            return new ResponseEntity<Address>(address, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Address>(famisService.saveAddress(address), HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable("addressId") UUID addressId, @RequestBody Address address, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (address == null) || (address.getCep() == null)){
            return new ResponseEntity<Address>(address, HttpStatus.BAD_REQUEST);
        }
        Address updatedAddress = this.famisService.updateAddress(addressId, address);

        if(updatedAddress == null){
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Address>(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Address> deleteById(@PathVariable("addressId") UUID addressId) {
        Address address = this.famisService.findAddressById(addressId);
        famisService.deleteAddress(address);
        if (address == null) {
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }
}