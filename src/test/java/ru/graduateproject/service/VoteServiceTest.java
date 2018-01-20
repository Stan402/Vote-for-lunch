package ru.graduateproject.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.graduateproject.DateTimeTestData;
import ru.graduateproject.UserTestData;
import ru.graduateproject.model.Dish;
import ru.graduateproject.model.Restaurant;
import ru.graduateproject.VoteTestData;
import ru.graduateproject.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static ru.graduateproject.DateTimeTestData.*;
import static ru.graduateproject.DishTestData.*;
import static ru.graduateproject.RestaurantTestData.*;
import static ru.graduateproject.UserTestData.*;
import static ru.graduateproject.VoteTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-mvc.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceTest {

    @Autowired
    VoteService voteService;

    public VoteServiceTest() {
    }

    @Test
    public void getVoid() throws Exception{
        Vote vote = voteService.getVote(USER_ID, DATE_11);
        assertMatch(vote, VOTE_USER_11);
    }

    @Test
    public void getRestaurant() throws Exception{
        Restaurant restaurant = voteService.getRestaurant(REST1_ID, DATE_11);
        assertMatch(restaurant, REST1);
        restaurant = voteService.getRestaurant(REST2_ID, DATE_12);
        assertNull(restaurant);
    }

    @Test
    public void getRestaurants() throws Exception {
        List<Restaurant> restaurants = voteService.getRestaurants(DATE_11);
        assertMatch(restaurants, REST1, REST2);
    }

    @Test
    public void getDishes() throws Exception {
        List<Dish> result = voteService.getDishes(DATE_11);
        assertMatch(result, DISHES_11);
    }

    @Test
    public void getVoteResult() throws Exception {
        Map<Integer, Integer> result = voteService.getVoteResult(DATE_11);
        assertEquals(RESULT_1.toString(), result.toString());
    }

    @Test
    public void getRestaurantsWithVotes() throws Exception {
    }

}