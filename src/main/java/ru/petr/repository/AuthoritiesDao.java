package ru.petr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.petr.entity.Authorities;

@Repository
public interface AuthoritiesDao extends CrudRepository<Authorities, Integer> {
}
