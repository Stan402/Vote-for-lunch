package ru.graduateproject.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.graduateproject.DateTimeTestData;
import ru.graduateproject.DishTestData;
import ru.graduateproject.RestaurantTestData;
import ru.graduateproject.TestUtil;
import ru.graduateproject.model.Dish;
import ru.graduateproject.model.Restaurant;
import ru.graduateproject.service.DishService;
import ru.graduateproject.service.RestaurantService;
import ru.graduateproject.to.DishTo;
import ru.graduateproject.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.graduateproject.DateTimeTestData.DATE_11;
import static ru.graduateproject.DateTimeTestData.DATE_12;
import static ru.graduateproject.DishTestData.*;
import static ru.graduateproject.DishTestData.assertMatch;
import static ru.graduateproject.RestaurantTestData.*;
import static ru.graduateproject.TestUtil.contentJson;
import static ru.graduateproject.TestUtil.contentJsonArray;

public class RestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantController.REST_URL + '/';
    private static final String REST_URL_DISH_REST1_DATE11 = REST_URL + REST1_ID + '/' + DATE_11 + '/';

    @Autowired
    RestaurantService service;

    @Autowired
    DishService dishService;

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(REST1, REST2));
    }

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + REST1_ID))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(REST1));
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + REST1_ID))
                .andExpect(status().isNoContent());
        RestaurantTestData.assertMatch(service.getAll(), REST2);

    }

    @Test
    public void update() throws Exception {
        Restaurant updated = new Restaurant(REST1_ID, "Updated rest", REST1.getCurrentMenuDate());
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + REST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        RestaurantTestData.assertMatch(service.get(REST1_ID), updated);
    }

    @Test
    public void createWithLocation() throws Exception {
        Restaurant created = new Restaurant(null, "Created rest", LocalDate.now());
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .param("name", created.getName()))
                .andExpect(status().isCreated());

        Restaurant returned = TestUtil.readFromJson(action, Restaurant.class);
        created.setId(returned.getId());

        RestaurantTestData.assertMatch(returned, created);
        RestaurantTestData.assertMatch(service.getAll(), REST1, REST2, created);
    }

    @Test
    public void showMenu() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL_DISH_REST1_DATE11))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonArray(DISH7, DISH8, DISH9, DISH10));
    }

    @Test
    public void getDish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL_DISH_REST1_DATE11 + DISH8_ID))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DISH8));
    }

    @Test
    public void deleteDish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL_DISH_REST1_DATE11 + DISH8_ID))
                .andExpect(status().isNoContent());
        assertMatch(dishService.getAllOnDate(DATE_11).stream()
                .filter(dish -> dish.getRestaurant().getId() == REST1_ID)
                .collect(Collectors.toList()), DISH7, DISH9, DISH10);
    }

    @Test
    public void updateDish() throws Exception {
        Dish updated = new Dish(DISH8_ID, "Updated Dish", 777, REST1, DATE_11);
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL_DISH_REST1_DATE11 + DISH8_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        assertMatch(dishService.get(REST1_ID, DISH8_ID), updated);
    }

    @Test
    public void createDishWithLocation() throws Exception {
        DishTo newDishTo = new DishTo("new Dish", 777);
        Dish expected = new Dish(null, newDishTo, REST1, DATE_12);
        ResultActions action = mockMvc
                .perform(MockMvcRequestBuilders.post(REST_URL + REST1_ID + '/' + DATE_12)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(newDishTo)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        Dish returned = TestUtil.readFromJson(action, Dish.class);
        expected.setId(returned.getId());
        assertMatch(returned, expected);
        assertMatch(dishService.getAllOnDate(DATE_12), expected);

    }

}