package com.model.dao.images;

import com.model.entities.Advert;
import com.model.entities.Images;

import java.util.List;

public interface ImagesDao {
    public Images findById(int id);

    public List<Images> findByAdvert(Advert advert);

    public Images getMainImages(Advert advert);
}
