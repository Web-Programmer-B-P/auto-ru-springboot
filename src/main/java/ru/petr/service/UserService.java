package ru.petr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petr.entity.User;
import ru.petr.repository.UserDao;

@Service
public class UserService {
    private static final int ENABLED_DEFAULT_VALUE = 1;
    private static final String SECURITY_PREFIX = "{noop}";
    @Autowired
    private UserDao userDao;

    public User findById(int id) {
        return userDao.findById(id).get();
    }

    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    public void save(User user) {
        String passwordSecurity = SECURITY_PREFIX + user.getPassword();
        user.setPassword(passwordSecurity);
        user.setEnabled(ENABLED_DEFAULT_VALUE);
        userDao.save(user);
    }
}
