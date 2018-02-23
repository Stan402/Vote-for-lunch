package ru.graduateproject.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduateproject.DateTimeTestData;
import ru.graduateproject.DishTestData;
import ru.graduateproject.RestaurantTestData;
import ru.graduateproject.model.Dish;
import ru.graduateproject.model.Restaurant;
import ru.graduateproject.to.DishTo;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static ru.graduateproject.DateTimeTestData.*;
import static ru.graduateproject.DishTestData.*;
import static ru.graduateproject.RestaurantTestData.*;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    DishService service;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void getAllOnDate() throws Exception {
        assertMatch(service.getAllOnDate(DATE_11), DISHES_11);
    }

    @Test
    public void get() throws Exception {
        assertMatch(service.get(REST1_ID, DISH8_ID), DISH8);
    }

    @Test
    public void delete() throws Exception {
        service.delete(DISH8_ID);
        assertMatch(service.getAllOnDate(DATE_11), DISH7, DISH9, DISH10, DISH11, DISH12, DISH13);
    }

    @Test
    public void create() throws Exception {
        Dish expected = new Dish(CREATED_DISH);
        Dish created = service.create(expected);
        expected.setId(created.getId());
        assertMatch(created, expected);
        assertMatch(service.getAllOnDate(DATE_11), DISH7, DISH8, DISH9, DISH10, DISH11, DISH12, DISH13, expected);
        assertMatch(created.getRestaurant(), REST1);

        //testing new date dish
        expected.setDate(DATE_15);
        expected.setId(null);
        Dish created2 = service.create(expected);
        expected.setId(created2.getId());
        assertMatch(created, expected);
        assertMatch(service.getAllOnDate(DATE_15), expected);
        assertMatch(restaurantService.get(REST1_ID), new Restaurant(REST1_ID, REST1.getName(), DATE_15));
    }

    @Test
    public void update() throws Exception {
        Dish updated = new Dish(DISH8);
        updated.setPrice(777);
        updated.setName("updated dish");
        updated.setDate(DATE_10);
        service.update(updated, DISH8_ID);
        assertMatch(service.get(REST1_ID, DISH8_ID), updated);
    }

    //testing new date update
    //TODO refactor - double code
    @Test
    public void updateNewDate() throws Exception {
        Dish updated = new Dish(DISH8);
        updated.setPrice(777);
        updated.setName("updated dish");
        updated.setDate(DATE_15);
        service.update(updated, DISH8_ID);
        assertMatch(service.get(REST1_ID, DISH8_ID), updated);
        assertMatch(service.getAllOnDate(DATE_15), updated);
        assertMatch(restaurantService.get(REST1_ID), new Restaurant(REST1_ID, REST1.getName(), DATE_15));
    }

    @Test
    public void createFromTo() throws Exception {
        Dish expected = new Dish(CREATED_DISH);
        DishTo dishTo = getTo(expected);
        Dish created = service.create(dishTo, DATE_11, REST1_ID);
        expected.setId(created.getId());
        assertMatch(created, expected);
    }
    @Test
    public void createFromToNewDate() throws Exception {
        Dish expected = new Dish(CREATED_DISH);
        expected.setDate(DATE_15);
        DishTo dishTo = getTo(expected);
        Dish created = service.create(dishTo, DATE_15, REST1_ID);
        expected.setId(created.getId());
        assertMatch(created, expected);
        assertMatch(service.getAllOnDate(DATE_15), expected);
        assertMatch(restaurantService.get(REST1_ID), new Restaurant(REST1_ID, REST1.getName(), DATE_15));
    }

}