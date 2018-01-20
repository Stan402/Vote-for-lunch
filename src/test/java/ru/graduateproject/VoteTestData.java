package ru.graduateproject;

import ru.graduateproject.model.Dish;
import ru.graduateproject.model.Vote;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduateproject.DateTimeTestData.DATE_11;
import static ru.graduateproject.RestaurantTestData.*;
import static ru.graduateproject.UserTestData.*;

public class VoteTestData {
    public static final Map<Integer, Integer> RESULT_1 = new HashMap<>();
    public static final Map<Integer, Integer> RESULT_2 = new HashMap<>();
    static {
        RESULT_1.put(100003, 0);
        RESULT_1.put(100004, 1);
        RESULT_2.put(100003, 1);
        RESULT_2.put(100004, 1);
    }
    public static final Vote VOTE_USER_11 = new Vote(100020, DATE_11, REST2, USER);


    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "restaurant");
    }
}
