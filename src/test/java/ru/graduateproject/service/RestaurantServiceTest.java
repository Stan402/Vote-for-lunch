package ru.graduateproject.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduateproject.RestaurantTestData;
import ru.graduateproject.model.Restaurant;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static ru.graduateproject.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService service;

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(), REST1, REST2);
    }

    @Test
    public void get() throws Exception {
        assertMatch(service.get(REST1_ID), REST1);
    }

    @Test
    public void delete() throws Exception {
        service.delete(REST1_ID);
        assertMatch(service.getAll(), REST2);
    }

    @Test
    public void create() throws Exception {
        Restaurant expected = RESTNEW;
        Restaurant created = service.create(RESTNEW);
        expected.setId(created.getId());
        assertMatch(service.getAll(), REST1, REST2, expected);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = new Restaurant(REST1);
        updated.setName("updated Rest");
        updated.setCurrentMenuDate(LocalDate.now());
        service.update(updated, REST1_ID);
        assertMatch(service.get(REST1_ID), updated);
    }

    @Test
    public void verifyDate() throws Exception {

    }

}