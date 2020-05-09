package ru.petr.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.petr.entity.Advertisement;
import java.util.List;

@Repository
public interface AdvertisementDao extends CrudRepository<Advertisement, Integer> {
    String FIND_ALL_BY_LAST_DATE = "SELECT * FROM Advertisement a WHERE a.create_date >= "
            + "(SELECT date_trunc('day', max(advert.create_date)) "
            + "FROM Advertisement AS advert) ORDER BY a.create_date DESC";

    @Query(value = FIND_ALL_BY_LAST_DATE, nativeQuery = true)
    List<Advertisement> findAllByLastDate();

    List<Advertisement> findAllByCar_CarBody(String carBody);
}
