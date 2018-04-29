package com.model.dao.advertType.impl;

import com.model.dao.HibernateUtil;
import com.model.dao.advertType.TypeDao;
import com.model.entities.AdvertType;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TypeDaoImpl implements TypeDao {
    public AdvertType findByName(String name) {
        Session session = openSession();
        try {
            List<AdvertType> advertTypeList = session.createCriteria(AdvertType.class).add(Restrictions.eq("typeName",name)).list();
            if (advertTypeList.isEmpty())
                return null;
            else
                return advertTypeList.get(0);
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
