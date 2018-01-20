package ru.graduateproject;

import ru.graduateproject.model.Dish;
import ru.graduateproject.model.Restaurant;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final Restaurant REST1 = new Restaurant(100003, "rest1", LocalDate.parse("2017-01-11"));
    public static final Restaurant REST2 = new Restaurant(100004, "rest2", LocalDate.parse("2017-01-11"));
    public static final int REST1_ID = 100003;
    public static final int REST2_ID = 100004;


    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);

    }
}
