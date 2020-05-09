package ru.petr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.petr.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findUserByName(String name);
}
