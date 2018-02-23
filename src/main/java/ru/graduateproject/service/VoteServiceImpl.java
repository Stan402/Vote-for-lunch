package ru.graduateproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.graduateproject.model.Dish;
import ru.graduateproject.model.Restaurant;
import ru.graduateproject.model.Vote;
import ru.graduateproject.repository.DishRepository;
import ru.graduateproject.repository.RestaurantRepository;
import ru.graduateproject.repository.UserRepository;
import ru.graduateproject.repository.VoteRepository;
import ru.graduateproject.to.RestaurantWithMenu;
import ru.graduateproject.to.RestaurantWithVotes;
import ru.graduateproject.util.RestaurantUtil;
import ru.graduateproject.util.ValidationUtil;
import ru.graduateproject.util.exception.LateVoteException;
import ru.graduateproject.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.graduateproject.util.ValidationUtil.checkTime;

@Service
public class VoteServiceImpl implements VoteService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final DishService dishService;

    @Autowired
    public VoteServiceImpl(DishRepository dishRepository, RestaurantRepository restaurantRepository, VoteRepository voteRepository, UserRepository userRepository, DishService dishService) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.dishService = dishService;
    }


    @Override
    @Transactional
    public Vote giveVote(int restId, int userId) throws NotFoundException, LateVoteException{
        checkTime();
        Vote vote = getVote(userId, LocalDate.now());
        if (vote == null) {
            vote = new Vote(null, LocalDate.now(), restaurantRepository.getOne(restId), userRepository.getOne(userId));
        } else {
            vote.setRestaurant(restaurantRepository.getOne(restId));
        }
        return voteRepository.save(vote);
    }

    @Override
    public Vote getVote(int userId, LocalDate localDate) {
        return voteRepository.getVote(userId, localDate);
    }


    @Override
    //@Cacheable("restaurants")
    public List<Restaurant> getRestaurants(LocalDate localDate) {
        return restaurantRepository.findAllByCurrentMenuDate(localDate);
    }

    @Override
    public Restaurant getRestaurant(int restId, LocalDate localDate) {
        return restaurantRepository.findByIdAndCurrentMenuDate(restId, localDate);
    }

    @Override
    //@Cacheable("votes")
    public Map<Integer, Integer> getVoteResult(LocalDate localDate) {
        List<Object[]> result = voteRepository.getVotesResult(localDate);
        Map<Integer, Integer> map = new HashMap<>();
        result.forEach(pair -> map.put((Integer) pair[0], ((Long) pair[1]).intValue()));

        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantWithVotes> getRestaurantsWithVotes(LocalDate date) {

        return RestaurantUtil.getRestaurantsWithVotes(getRestaurants(date), dishService.getAllOnDate(date), getVoteResult(date));
    }

    @Override
    public RestaurantWithMenu getRestaurantWithMenu(int restId, LocalDate date) {
        return null;
    }

}
