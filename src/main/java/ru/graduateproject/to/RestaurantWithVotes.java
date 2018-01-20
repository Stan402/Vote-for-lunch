package ru.graduateproject.to;

import ru.graduateproject.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantWithVotes extends BaseTo{

    private String name;

    private LocalDateTime dateTime;

    private List<Dish> menu;

    private int votes;

    public RestaurantWithVotes() {
    }

    public RestaurantWithVotes(Integer id, String name, List<Dish> menu, LocalDateTime dateTime, int votes) {
        super(id);
        this.name = name;
        this.menu = menu;
        this.dateTime = dateTime;
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
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
