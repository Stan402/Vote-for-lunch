package ru.graduateproject.to;


public class DishTo extends BaseTo {

    private String name;

    private int price;

    public DishTo() {
    }

    public DishTo(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
