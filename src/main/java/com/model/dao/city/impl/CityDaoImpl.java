package com.model.dao.city.impl;

import com.model.dao.HibernateUtil;
import com.model.dao.city.CityDao;
import com.model.entities.City;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CityDaoImpl implements CityDao {
    public City findById(int id) {
        Session session = openSession();
        try {
            List<City> cityList = session.createCriteria(City.class).add(Restrictions.eq("cityId",id)).list();
            return cityList.get(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public City findByName(String name) {
        Session session = openSession();
        try{
            List<City> cityList = session.createCriteria(City.class).add(Restrictions.eq("cityName", name)).list();
            if (cityList.isEmpty())
                return null;
            else
                return cityList.get(0);
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
