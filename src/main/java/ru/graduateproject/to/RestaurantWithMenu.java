package ru.graduateproject.to;

import ru.graduateproject.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantWithMenu extends BaseTo{
    protected String name;

    protected LocalDateTime dateTime;

    protected List<Dish> menu;

    public RestaurantWithMenu() {
    }

    public RestaurantWithMenu(Integer id, String name, LocalDateTime dateTime, List<Dish> menu) {
        super(id);
        this.name = name;
        this.dateTime = dateTime;
        this.menu = menu;
    }

    public RestaurantWithMenu(RestaurantWithMenu restaurant){
        this(restaurant.getId(), restaurant.getName(), restaurant.getDateTime(), restaurant.getMenu());
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

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "RestaurantWithMenu{" +
                "name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", menu=" + menu +
                ", id=" + id +
                '}';
    }
}
