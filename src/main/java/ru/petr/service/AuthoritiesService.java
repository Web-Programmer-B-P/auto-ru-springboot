package ru.petr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petr.entity.Authorities;
import ru.petr.repository.AuthoritiesDao;

@Service
public class AuthoritiesService {
    @Autowired
    private AuthoritiesDao authoritiesDao;

    public void createNewAuthorityByUserName(String userName) {
        authoritiesDao.save(new Authorities(userName, "ROLE_USER"));
    }
}
