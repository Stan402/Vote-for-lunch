package ru.graduateproject.to;

import ru.graduateproject.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantWithVotes extends RestaurantWithMenu{


    private int votes;

    public RestaurantWithVotes() {
    }

    public RestaurantWithVotes(Integer id, String name, List<Dish> menu, LocalDateTime dateTime, int votes) {
        super(id, name, dateTime, menu);
        this.votes = votes;
    }

    public RestaurantWithVotes(RestaurantWithMenu restaurant, int votes){
        super(restaurant);
        this.votes = votes;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "RestaurantWithVotes{" +
                "name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", menu=" + menu +
                ", votes=" + votes +
                ", id=" + id +
                '}';
    }
}
