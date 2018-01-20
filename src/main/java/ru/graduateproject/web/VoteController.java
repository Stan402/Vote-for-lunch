package ru.graduateproject.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduateproject.service.VoteService;
import ru.graduateproject.to.RestaurantWithVotes;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    public static final String REST_URL = "/rest/profile/restaurants";

    @Autowired
    VoteService voteService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping
    public List<RestaurantWithVotes> getRestaurants() {
        return voteService.getRestaurantsWithVotes(LocalDate.now());
    }

    @PostMapping("/{restId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void giveVote(@PathVariable("restId") int restId){

    }

}
