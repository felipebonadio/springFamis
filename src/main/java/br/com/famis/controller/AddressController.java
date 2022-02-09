package br.com.famis.controller;

import br.com.famis.model.Address;
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
@RequestMapping("/addresses")
public class AddressController {

    private final FamisService famisService;

    public AddressController (FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAdress(@PathVariable("addressId") UUID addressId){
        Optional<Address> address = this.famisService.findAddressById(addressId);
        return address.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAddresses(){
        List<Address> addresses = this.famisService.findAllAddresses();
        if(addresses.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addresses);
    }

    @PostMapping
    public ResponseEntity<Address> saveAddress(@RequestBody @Valid  Address address, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (address == null) || (address.getCep() == null)){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(famisService.saveAddress(address), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Address> updateAddress(@RequestBody Address address, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (address == null) || (address.getCep() == null)){
            return ResponseEntity.badRequest().build();
        }
        Optional<Address> updatedAddress = this.famisService.updateAddress(address);
        return updatedAddress.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Address> deleteById(@PathVariable("addressId") UUID addressId) {
        Optional<Address> address = this.famisService.findAddressById(addressId);
        if(address.isPresent()) {
            famisService.deleteAddress(address.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}