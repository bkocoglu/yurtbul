package com.dao.impl;

import com.utils.HibernateUtil;
import com.dao.CommentDao;
import com.model.entities.Advert;
import com.model.entities.Comment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
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

    public List<Comment> getNotifiedComment(Advert advert) {
        Session session = openSession();
        try {
            List<Comment> commentList = session.createCriteria(Comment.class).add(Restrictions.and(
                    Restrictions.eq("advert",advert),
                    Restrictions.eq("approval",true)
            )).list();
            return commentList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public Comment findById(int id) {
        Session session = openSession();
        try {
            List<Comment> commentList = session.createCriteria(Comment.class).add(Restrictions.eq("commentId",id)).list();
            if (commentList.isEmpty() || commentList==null){
                return null;
            }else {
                return commentList.get(0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    public List<Integer> getStars(Advert advert) {
        Session session = openSession();
        try {
            Criteria criteria = session.createCriteria(Comment.class);
            criteria.setProjection(Projections.projectionList().add(Projections.property("star")));
            criteria.add(Restrictions.and(
                    Restrictions.eq("advert",advert),
                    Restrictions.eq("approval",true)
            ));
            List<Integer> stars = criteria.list();
            if (stars.isEmpty())
                return null;
            else
                return stars;
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
