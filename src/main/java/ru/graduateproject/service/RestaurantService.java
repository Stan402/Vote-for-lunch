package ru.graduateproject.service;


import ru.graduateproject.model.Restaurant;
import ru.graduateproject.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAll();

    Restaurant get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant, int id) throws NotFoundException;

    void verifyDate(int restId, LocalDate date);
}
