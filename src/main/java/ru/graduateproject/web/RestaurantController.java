package ru.graduateproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduateproject.model.Dish;
import ru.graduateproject.model.Restaurant;
import ru.graduateproject.service.DishService;
import ru.graduateproject.service.RestaurantService;
import ru.graduateproject.service.VoteService;
import ru.graduateproject.to.DishTo;
import ru.graduateproject.util.ValidationUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ru.graduateproject.util.ValidationUtil.*;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    RestaurantService service;

    @Autowired
    DishService dishService;

    @GetMapping
    public List<Restaurant> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        assureIdConsistent(restaurant, id);
        service.update(restaurant, id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Restaurant> createWithLocation(@RequestParam String name) {
        Restaurant restaurant = new Restaurant(null, name, LocalDate.now());
        Restaurant created = service.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/{id}/{date}")
    public List<Dish> showMenu(@PathVariable("id") int restId, @PathVariable("date") LocalDate date) {
        return dishService.getAllOnDate(date).stream()
                .filter(dish -> dish.getRestaurant().getId() == restId)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{restId}/{date}/{dishId}")
    public Dish getDish(@PathVariable("restId") int restId, @PathVariable("dishId") int dishId) {
        return dishService.get(restId, dishId);
    }

    @DeleteMapping("/{restId}/{date}/{dishId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable("dishId") int id) {
        dishService.delete(id);
    }

    @PutMapping(value = "/{restId}/{date}/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDish(@RequestBody Dish dish, @PathVariable("dishId") int dishId) {
        assureIdConsistent(dish, dishId);
        dishService.update(dish, dishId);
    }

    @PostMapping(value = "/{restId}/{date}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Dish> createDishWithLocation(@PathVariable("restId") int restId
            , @PathVariable("date") LocalDate date
            , @RequestBody DishTo dishTo) {
        Dish created = dishService.create(dishTo, date, restId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restId}/{date}/{id}")
                .buildAndExpand(restId, date, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
