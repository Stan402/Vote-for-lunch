package ru.graduateproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduateproject.model.Dish;
import ru.graduateproject.model.Restaurant;
import ru.graduateproject.repository.DishRepository;
import ru.graduateproject.repository.RestaurantRepository;
import ru.graduateproject.to.DishTo;
import ru.graduateproject.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.graduateproject.util.ValidationUtil.checkNotFoundWithId;

public class DishServiceImpl implements DishService{

    @Autowired
    DishRepository dishRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantService restaurantService;

    @Override
    public List<Dish> getAllOnDate(LocalDate localDate) {
        return dishRepository.findAllByDate(localDate);
    }

    @Override
    public Dish get(int restId, int id) throws NotFoundException {
        return checkNotFoundWithId(dishRepository.findByIdAndRestId(id, restId), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(dishRepository.delete(id) != 0, id);
    }

    @Override
    @Transactional
    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        restaurantService.verifyDate(dish.getRestaurant().getId(), dish.getDate());
        return  dishRepository.save(dish);
    }

    @Override
    @Transactional
    public void update(Dish dish, int id) throws NotFoundException {
        Assert.notNull(dish, "dish must not be null");
        restaurantService.verifyDate(dish.getRestaurant().getId(), dish.getDate());
        checkNotFoundWithId(dishRepository.save(dish), id);
    }

    @Override
    @Transactional
    public Dish create(DishTo dishTo, LocalDate date, int restId) {
        Dish dish = new Dish(null, dishTo.getName(), dishTo.getPrice(), restaurantRepository.getOne(restId), date );
        restaurantService.verifyDate(restId, date);
        return dishRepository.save(dish);
    }
}
