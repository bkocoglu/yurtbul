package com.model.dao.user.impl;

import com.model.dao.HibernateUtil;
import com.model.dao.user.UserDao;
import com.model.entities.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDabImpl implements UserDao {

    public boolean findBySystemName(String systemName) {
        Session session = openSession();
        session.getTransaction().begin();
        try {
            List<User> userList = session.createCriteria(User.class).add(Restrictions.eq("eMail",systemName)).list();
            System.out.println(userList);
            if (userList.isEmpty())
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }finally {
            session.close();
        }
    }

    public User logCheck(String username, String password) {
        Session session = openSession();
        session.getTransaction().begin();
        try{
            Criteria criteria = session.createCriteria(User.class);

            Criterion nameCriterion = Restrictions.eq("eMail",username);
            Criterion passCriterion = Restrictions.eq("pass",password);

            LogicalExpression andExpression = Restrictions.and(nameCriterion,passCriterion);
            criteria.add(andExpression);
            List<User> userList = criteria.list();
            if (userList.isEmpty())
                return null;
            else
                return userList.get(0);
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
