package ru.graduateproject.util;

import ru.graduateproject.model.Dish;
import ru.graduateproject.model.Restaurant;
import ru.graduateproject.to.RestaurantWithVotes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestaurantUtil {

public static List<RestaurantWithVotes> getRestaurantsWithVotes(List<Restaurant> restaurants, List<Dish> dishes, Map<Integer, Integer> votes){

    return restaurants.stream()
            .map(restaurant -> createRestaurantWithVotes(restaurant
                    , dishes.stream().filter(dish -> dish.getRestaurant().equals(restaurant)).collect(Collectors.toList())
                    , votes.get(restaurant.getId())))
            .collect(Collectors.toList());
}

private static RestaurantWithVotes createRestaurantWithVotes(Restaurant restaurant, List<Dish> menu, int votes){
    return new RestaurantWithVotes(restaurant.getId(), restaurant.getName(), menu, LocalDateTime.now(), votes );
}

}
