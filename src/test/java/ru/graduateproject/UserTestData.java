package ru.graduateproject;

import ru.graduateproject.model.AbstractBaseEntity;
import ru.graduateproject.model.Role;
import ru.graduateproject.model.User;

import static ru.graduateproject.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 2;
    public static final int USER1_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "password", Role.ROLE_USER);
    public static final User USER1 = new User(USER1_ID, "User1", "password1", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin", Role.ROLE_ADMIN);
}
