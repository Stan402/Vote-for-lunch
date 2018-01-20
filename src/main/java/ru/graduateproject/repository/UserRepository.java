package ru.graduateproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduateproject.model.User;

import java.util.List;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer>{


}
