package com.test;


import com.utils.HibernateUtil;
import org.hibernate.Session;

public class HibernateExample {
    public static void save(Object entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try{
            session.save(entity);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            System.out.println(session.getTransaction().getStatus());
            session.close();
        }
    }
}
