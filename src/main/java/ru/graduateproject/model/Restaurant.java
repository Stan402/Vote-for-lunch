package ru.graduateproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx")})
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "current_menu_date", nullable = false)
    @NotNull
    private LocalDate currentMenuDate;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, @NotNull LocalDate currentMenuDate) {
        super(id, name);
        this.currentMenuDate = currentMenuDate;
    }

    public LocalDate getCurrentMenuDate() {
        return currentMenuDate;
    }

    public void setCurrentMenuDate(LocalDate currentMenuDate) {
        this.currentMenuDate = currentMenuDate;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "currentMenuDate=" + currentMenuDate +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
