package com.dao;

import com.model.entities.Advert;
import com.model.entities.AdvertType;
import com.model.entities.City;
import com.model.entities.User;

import java.util.List;

public interface AdvertDao {

    public List<Advert> findByUser(User user);

    public Advert findById(int id);

    public List<Advert> filters(City city, AdvertType advertType);

    public List<Advert> filters(City city);

}
