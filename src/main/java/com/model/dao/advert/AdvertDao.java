package com.model.dao.advert;

import com.model.entities.Advert;
import com.model.entities.User;

import java.util.List;

public interface AdvertDao {

    public List<Advert> findByUser(User user);

    public Advert findById(int id);

}
