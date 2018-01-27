package ru.graduateproject.service;

import org.springframework.stereotype.Service;
import ru.graduateproject.model.Dish;
import ru.graduateproject.model.Restaurant;
import ru.graduateproject.to.DishTo;
import ru.graduateproject.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public interface DishService {

    List<Dish> getAllOnDate(LocalDate localDate);

    Dish get(int restId, int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    Dish create(Dish dish);

    void update(Dish dish, int id) throws NotFoundException;

    Dish create(DishTo dishTo, LocalDate date, int restId);
}


