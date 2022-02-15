package br.com.famis.controller;

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
@RequestMapping("/restaurants")
public class RestaurantController {

    private final FamisService famisService;

    public RestaurantController( FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("restaurantId") UUID restaurantId) {
        Optional<Restaurant> restaurant = this.famisService.findRestaurantById(restaurantId);
        return restaurant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        List<Restaurant> restaurants = this.famisService.findAllRestaurants();
        if (restaurants.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurants);
    }

    @PostMapping
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody @Valid Restaurant restaurant, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (restaurant == null) || (restaurant.getName() == null)){
            return ResponseEntity.badRequest().build();
        }
        if(restaurant.getAddress() != null){
            famisService.saveAddress(restaurant.getAddress());
        }
        return new ResponseEntity<>(famisService.saveRestaurant(restaurant), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Optional<Restaurant> updatedRestaurant = this.famisService.updateRestaurant(restaurant);
        return updatedRestaurant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/consumer")
    public ResponseEntity<Restaurant> updateConsumerOnRestaurant(@RequestBody Restaurant restaurant, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Optional<Restaurant> updatedRestaurant = this.famisService.updateConsumerOnRestaurant(restaurant);
        return updatedRestaurant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Consumer> deleteById(@PathVariable("restaurantId") UUID restaurantId) {
        Optional<Restaurant> restaurant = this.famisService.findRestaurantById(restaurantId);
        if(restaurant.isPresent()) {
            famisService.deleteRestaurant(restaurant.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

