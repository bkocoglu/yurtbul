package com.model.dao.advertType;

import com.model.entities.AdvertType;

public interface TypeDao {
    public AdvertType findByName(String name);
}
