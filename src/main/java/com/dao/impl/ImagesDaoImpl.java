package com.dao.impl;

import com.utils.HibernateUtil;
import com.dao.ImagesDao;
import com.model.entities.Advert;
import com.model.entities.Images;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
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

    public List<Images> findByAdvert(Advert advert) {
        Session session = openSession();
        try {
            List<Images> list = session.createCriteria(Images.class).add(Restrictions.eq("advert",advert)).list();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public Images getMainImages(Advert advert) {
        Session session = openSession();
        try{
            String mainName = "mainPhoto";
            List<Images> imagesList = session.createCriteria(Images.class).add(Restrictions.and(
                    Restrictions.eq("advert",advert), Restrictions.eq("name", mainName)
            )).list();
            if (imagesList==null || imagesList.isEmpty())
                return null;
            else
                return imagesList.get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public List<Integer> getImagesId(Advert advert) {
        Session session = openSession();
        try {
            Criteria criteria = session.createCriteria(Images.class);
            criteria.setProjection(Projections.projectionList().add(Projections.property("imageId")));
            criteria.add(Restrictions.eq("advert",advert));
            List<Integer> imageList = criteria.list();
            System.out.println(imageList);

            return imageList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
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
