package ru.graduateproject.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduateproject.model.Role;
import ru.graduateproject.model.User;

import java.util.Arrays;
import java.util.Collections;

import static ru.graduateproject.UserTestData.*;
import static ru.graduateproject.UserTestData.USERS;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    UserService service;

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(), USERS);
    }

    @Test
    public void get() throws Exception {
        assertMatch(service.get(USER_ID), USER);
    }

    @Test
    public void create() throws Exception {
        User expected = new User(NEW_USER);
        User created = service.create(expected);
        expected.setId(created.getId());
        assertMatch(created, expected);
        assertMatch(service.getAll(), USER, USER1, ADMIN, expected);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), USER1, ADMIN);
    }

    @Test
    public void update() throws Exception {
        User updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(USER_ID), updated);
    }

}