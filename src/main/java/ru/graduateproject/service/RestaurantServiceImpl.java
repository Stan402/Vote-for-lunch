package ru.graduateproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduateproject.model.Restaurant;
import ru.graduateproject.repository.DishRepository;
import ru.graduateproject.repository.RestaurantRepository;
import ru.graduateproject.util.ValidationUtil;
import ru.graduateproject.util.exception.NotFoundException;

import java.util.List;

import static ru.graduateproject.util.ValidationUtil.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    final
    private RestaurantRepository restaurantRepository;
    final
    private DishRepository dishRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return  restaurantRepository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant, int id) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant), id);
    }


}
