package com.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by pC2 on 25.07.2017.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try{
            return new Configuration().configure().buildSessionFactory();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutdown(){
        getSessionFactory().close();
    }
}