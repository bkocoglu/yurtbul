package com.dao;

import com.model.entities.City;

import java.util.List;

public interface CityDao {
    public City findById (int id);

    public City findByName (String name);
}
