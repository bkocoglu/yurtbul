package com.dao;

import com.model.entities.AdvertType;

public interface TypeDao {
    public AdvertType findByName(String name);
}
