package ru.graduateproject;

import ru.graduateproject.model.Dish;
import ru.graduateproject.to.DishTo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduateproject.RestaurantTestData.*;

public class DishTestData {

    public final static Dish DISH1 = new Dish(100005, "dish1", 1000, REST1, LocalDate.parse("2017-01-10"));
    public final static Dish DISH2 = new Dish(100006, "dish2", 1500, REST1, LocalDate.parse("2017-01-10"));
    public final static Dish DISH3 = new Dish(100007, "dish3", 1700, REST1, LocalDate.parse("2017-01-10"));
    public final static Dish DISH4 = new Dish(100008, "dish4", 500, REST1, LocalDate.parse("2017-01-10"));
    public final static Dish DISH5 = new Dish(100009, "dish1", 1000, REST2, LocalDate.parse("2017-01-10"));
    public final static Dish DISH6 = new Dish(100010, "dish2", 1300, REST2, LocalDate.parse("2017-01-10"));
    public final static Dish DISH7 = new Dish(100011, "dish1", 2000, REST1, LocalDate.parse("2017-01-11"));
    public final static Dish DISH8 = new Dish(100012, "dish2", 2500, REST1, LocalDate.parse("2017-01-11"));
    public final static Dish DISH9 = new Dish(100013, "dish3", 2700, REST1, LocalDate.parse("2017-01-11"));
    public final static Dish DISH10 = new Dish(100014, "dish4", 700, REST1, LocalDate.parse("2017-01-11"));
    public final static Dish DISH11 = new Dish(100015, "dish1", 2000, REST2, LocalDate.parse("2017-01-11"));
    public final static Dish DISH12 = new Dish(100016, "dish2", 2300, REST2, LocalDate.parse("2017-01-11"));
    public final static Dish DISH13 = new Dish(100017, "dish3", 2300, REST2, LocalDate.parse("2017-01-11"));

    public final static int DISH8_ID = 100012;

    public final static Dish CREATED_DISH = new Dish(null, "new dish", 777, REST1, LocalDate.parse("2017-01-11"));


    public static final List<Dish> DISHES_11 = Arrays.asList(DISH7, DISH8, DISH9, DISH10, DISH11, DISH12, DISH13);

    public static DishTo getTo(Dish dish){
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);

    }
}
