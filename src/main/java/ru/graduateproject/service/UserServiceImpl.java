package ru.graduateproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduateproject.model.User;
import ru.graduateproject.repository.UserRepository;
import ru.graduateproject.util.ValidationUtil;

import java.util.List;

import static ru.graduateproject.util.ValidationUtil.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(int id) {
        return checkNotFoundWithId(userRepository.findById(id).orElse(null), id);
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
           checkNotFoundWithId(userRepository.delete(id) != 0, id);
    }

    @Override
    public void update(User user, int id) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(userRepository.save(user), id);

    }


}
