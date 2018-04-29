package com.model.dao.images.impl;

import com.model.dao.HibernateUtil;
import com.model.dao.images.ImagesDao;
import com.model.entities.Images;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ImagesDaoImpl implements ImagesDao {
    public Images findById(int id) {
        Session session = openSession();
        try {
            List<Images> imagesList = session.createCriteria(Images.class).add(Restrictions.eq("imageId",id)).list();
            if (imagesList.isEmpty())
                return null;
            return imagesList.get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }
    private Session openSession(){
        try {
            return HibernateUtil.getSessionFactory().openSession();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
