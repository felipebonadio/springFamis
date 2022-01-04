package br.com.famis.controller;

import br.com.famis.model.Restaurant;
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
@RequestMapping("/restaurants")
public class RestaurantController {

    private FamisService famisService;

    public RestaurantController( FamisService famisService){
        this.famisService = famisService;
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("restaurantId") UUID restaurantId) {
        Restaurant restaurant = this.famisService.findRestaurantById(restaurantId);
        if (restaurant == null) {
            return new ResponseEntity<Restaurant>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        List<Restaurant> restaurants = this.famisService.findAllRestaurants();
        if (restaurants.isEmpty()) {
            return new ResponseEntity<List<Restaurant>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody @Valid Restaurant restaurant, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (restaurant == null) || (restaurant.getName() == null)){
            return new ResponseEntity<Restaurant>(restaurant, HttpStatus.BAD_REQUEST);
        }
        if(restaurant.getAddress() != null){
            famisService.saveAddress(restaurant.getAddress());
        }
        return new ResponseEntity<Restaurant>(famisService.saveRestaurant(restaurant), HttpStatus.CREATED);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("restaurantId") UUID restaurantId, @RequestBody Restaurant restaurant, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (restaurant == null) || (restaurant.getName() == null)){
            return new ResponseEntity<Restaurant>(restaurant, HttpStatus.BAD_REQUEST);
        }
        Restaurant currentRestaurant = this.famisService.updateRestaurant(restaurantId, restaurant);
        if(currentRestaurant == null){
            return new ResponseEntity<Restaurant>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Restaurant>(currentRestaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> deleteById(@PathVariable("restaurantId") UUID restaurantId) {
        Restaurant restaurant = this.famisService.findRestaurantById(restaurantId);
        famisService.deleteRestaurant(restaurant);
        if (restaurantId == null) {
            return new ResponseEntity<Restaurant>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }
}

