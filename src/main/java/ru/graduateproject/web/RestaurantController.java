package ru.graduateproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduateproject.model.Restaurant;
import ru.graduateproject.service.RestaurantService;
import ru.graduateproject.util.ValidationUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.graduateproject.util.ValidationUtil.*;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    static final String REST_URL = "rest/admin/restaurants";

    @Autowired
    RestaurantService service;

    @GetMapping
    public List<Restaurant> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id){
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        assureIdConsistent(restaurant, id);
        service.update(restaurant, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestParam String name) {
        Restaurant restaurant = new Restaurant(null, name, LocalDate.now());
        Restaurant created = service.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}
