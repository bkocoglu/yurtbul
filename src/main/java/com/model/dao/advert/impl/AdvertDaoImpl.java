package com.model.dao.advert.impl;

import com.model.dao.HibernateUtil;
import com.model.dao.advert.AdvertDao;
import com.model.entities.Advert;
import com.model.entities.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AdvertDaoImpl implements AdvertDao {
    public List<Advert> findByUser(User user) {
        Session session = openSession();
        try {
            List<Advert> advertList = session.createCriteria(Advert.class).add(Restrictions.eq("user",user)).list();
            return advertList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public Advert findById(int id) {
        Session session = openSession();
        try{
            List<Advert> advertList = session.createCriteria(Advert.class).add(Restrictions.eq("id",id)).list();
            if (advertList.isEmpty())
                return null;
            else
                return advertList.get(0);
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
