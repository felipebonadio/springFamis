package br.com.famis.controller;


import br.com.famis.model.Restaurant;
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
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private FamisService famisService;

    @RequestMapping(value = "/{restaurantId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("restaurantId") UUID restaurantId) {
        Restaurant restaurant = this.famisService.findRestaurantById(restaurantId);
        if (restaurant == null) {
            return new ResponseEntity<Restaurant>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        List<Restaurant> restaurants = this.famisService.findAllRestaurants();
        if (restaurants.isEmpty()) {
            return new ResponseEntity<List<Restaurant>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant restaurant, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (restaurant == null) || (restaurant.getName() == null)){
            return new ResponseEntity<Restaurant>(restaurant, HttpStatus.BAD_REQUEST);
        }
        if(restaurant.getAddress() != null){
            famisService.saveAddress(restaurant.getAddress());
        }
        return new ResponseEntity<Restaurant>(famisService.saveRestaurant(restaurant), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{restaurantId}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("restaurantId") UUID restaurantId, @RequestBody Restaurant restaurant, BindingResult bindingResult){
        if(bindingResult.hasErrors() || (restaurant == null) || (restaurant.getName() == null)){
            return new ResponseEntity<Restaurant>(restaurant, HttpStatus.BAD_REQUEST);
        }
        Restaurant updatedRestaurant = this.famisService.updateRestaurant(restaurantId, restaurant);
        if(updatedRestaurant == null){
            return new ResponseEntity<Restaurant>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Restaurant>(updatedRestaurant, HttpStatus.OK);
    }

    @RequestMapping(value = "/{restaurantId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Restaurant> deleteById(@PathVariable("restaurantId") UUID restaurantId) {
        Restaurant restaurant = this.famisService.findRestaurantById(restaurantId);
        famisService.deleteRestaurant(restaurant);
        if (restaurantId == null) {
            return new ResponseEntity<Restaurant>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }
}

