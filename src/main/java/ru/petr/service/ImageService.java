package ru.petr.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petr.entity.Image;
import ru.petr.repository.ImageDao;
import java.util.Objects;

@Service
public class ImageService {
    private static final Logger LOG = LogManager.getLogger(ImageService.class.getName());
    @Autowired
    private ImageDao imageDao;

    public Image findByAdvertisementId(int id) {
        return imageDao.findImageByAdvertisementId(id);
    }

    public void saveImage(Image image) {
        if (Objects.nonNull(image)) {
            imageDao.save(image);
        } else {
            LOG.error("Смотри сохранение изображения что то пошло не так");
        }
    }
}
