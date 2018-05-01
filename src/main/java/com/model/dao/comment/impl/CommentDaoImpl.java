package com.model.dao.comment.impl;

import com.model.dao.HibernateUtil;
import com.model.dao.comment.CommentDao;
import com.model.entities.Advert;
import com.model.entities.Comment;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CommentDaoImpl implements CommentDao {
    public List<Comment> getPendingComment(Advert advert) {
        Session session = openSession();
        try {
            List<Comment> commentList = session.createCriteria(Comment.class).add(Restrictions.and(
                    Restrictions.eq("advert",advert),Restrictions.eq("approval",false)
            )).list();

            return commentList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public List<Comment> findByAdvert(Advert advert) {
        Session session = openSession();
        try {
            List<Comment> commentList = session.createCriteria(Comment.class).add(Restrictions.eq("advert",advert)).list();
            return commentList;
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
