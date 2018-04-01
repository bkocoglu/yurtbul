package com.test;

import com.model.dao.HibernateUtil;
import com.model.entities.Advert;
import com.model.entities.AdvertType;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ManyToOneTest {
    public static void main(String [] args){
       /* AdvertType type1 = new AdvertType("apart");
        AdvertType type2 = new AdvertType("yurt");

        Advert advert1 = new Advert("Hasan Canturk", 750, "gir ve gör", type2);
        Advert advert2 = new Advert("Hasan Canturk Yurdu", 650, "gir ve gör2", type2);
        Advert advert3 = new Advert("Hasan Canturk Apartı", 950, "gir ve gör", type1);

        HibernateExample.save(advert1);
        HibernateExample.save(advert2);
        HibernateExample.save(advert3);
        */
       Session session = HibernateUtil.getSessionFactory().openSession();
       Query query = session.getNamedQuery("getTypeByName");
       query.setParameter("typeName", "apart");
       AdvertType advertType = (AdvertType) query.getSingleResult();

       Advert advert4 = new Advert("Ada apart", 1050, "gir ve gör2", advertType);

        HibernateExample.save(advert4);

    }
}
