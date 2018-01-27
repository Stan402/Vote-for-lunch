package ru.graduateproject.service;

import ru.graduateproject.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User get(int id);

    User create(User user);

    void delete(int id);

    void update(User user, int id);
}
