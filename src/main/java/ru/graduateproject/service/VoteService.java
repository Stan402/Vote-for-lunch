package ru.graduateproject.service;

import ru.graduateproject.model.Dish;
import ru.graduateproject.model.Restaurant;
import ru.graduateproject.model.Vote;
import ru.graduateproject.to.RestaurantWithMenu;
import ru.graduateproject.to.RestaurantWithVotes;
import ru.graduateproject.util.exception.LateVoteException;
import ru.graduateproject.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface VoteService {

    Vote giveVote(int restId, int userId) throws NotFoundException, LateVoteException;

    Vote getVote(int userId, LocalDate localDate);

    List<Restaurant> getRestaurants(LocalDate localDate);

    Restaurant getRestaurant(int restId, LocalDate localDate);

    Map<Integer, Integer> getVoteResult(LocalDate localDate);

    List<RestaurantWithVotes> getRestaurantsWithVotes(LocalDate now);

    RestaurantWithMenu getRestaurantWithMenu(int restId, LocalDate date);
}
