package ru.petr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.petr.entity.Image;

@Repository
public interface ImageDao extends CrudRepository<Image, Integer> {
    Image findImageByAdvertisementId(int advertisementId);
}
