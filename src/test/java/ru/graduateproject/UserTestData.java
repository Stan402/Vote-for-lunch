package ru.graduateproject;

import ru.graduateproject.model.Role;
import ru.graduateproject.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduateproject.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 2;
    public static final int USER1_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "password", Role.ROLE_USER);
    public static final User USER1 = new User(USER1_ID, "User1", "password1", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin", Role.ROLE_ADMIN);
    public static final User NEW_USER = new User(null, "New User", "new password", Role.ROLE_USER);

    public static final List<User> USERS = Arrays.asList(USER, USER1, ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static User getUpdated(){
        User updated = new User(USER);
        updated.setName("updated User");
        updated.setPassword("new password");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        return updated;
    }
}
