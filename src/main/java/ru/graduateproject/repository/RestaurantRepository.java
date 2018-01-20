package ru.graduateproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduateproject.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r FROM Restaurant r WHERE r.currentMenuDate=:localDate")
    List<Restaurant> findAllByCurrentMenuDate(@Param("localDate") LocalDate localDate);

    Restaurant findByIdAndCurrentMenuDate(int restId, LocalDate localDate);
}
