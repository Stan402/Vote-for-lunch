package ru.graduateproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduateproject.model.Restaurant;
import ru.graduateproject.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer>{

    @Query(value = "SELECT r.id, COUNT(v.id)\n" +
            "  FROM Restaurant r LEFT JOIN Vote v ON (v.restaurant = r AND v.date=:localDate)\n" +
            " GROUP BY r.id")
    List<Object[]> getVotesResult(@Param("localDate") LocalDate localDate);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.date=:localDate")
    Vote getVote(@Param("userId") int userId, @Param("localDate") LocalDate localDate);
}
