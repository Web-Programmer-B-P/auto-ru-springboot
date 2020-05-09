package ru.petr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petr.entity.Advertisement;
import ru.petr.entity.Status;
import ru.petr.entity.User;
import ru.petr.repository.AdvertisementDao;
import ru.petr.repository.UserDao;
import ru.petr.utils.Utils;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AdvertisementService {
    @Autowired
    private AdvertisementDao dao;

    @Autowired
    private UserDao userDao;

    public List<Advertisement> findAll() {
        return (List<Advertisement>) dao.findAll();
    }

    public Advertisement findById(int id) {
        return dao.findById(id).get();
    }

    public List<Advertisement> findByLastDate() {
        return dao.findAllByLastDate();
    }

    public List<Advertisement> findByCarBody(String carBody) {
        return dao.findAllByCar_CarBody(carBody);
    }

    public int save(Advertisement advertisement) {
        User user = userDao.findUserByName(Utils.getSessionUserName());
        advertisement.setUserId(user.getId());
        advertisement.setCreateDate(new Timestamp(System.currentTimeMillis()));
        return dao.save(advertisement).getId();
    }

    public void changeSaleStatus(Status status) {
        Advertisement advertisement = findById(status.getId());
        advertisement.setSaleStatus(status.getSaleStatus());
        dao.save(advertisement);
    }

}
