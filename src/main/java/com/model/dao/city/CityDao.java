package com.model.dao.city;

import com.model.entities.City;

import java.util.List;

public interface CityDao {
    public City findById (int id);

    public City findByName (String name);
}
