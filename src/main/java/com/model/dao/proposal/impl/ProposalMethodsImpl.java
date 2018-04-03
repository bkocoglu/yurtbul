package com.model.dao.proposal.impl;

import com.model.dao.HibernateUtil;
import com.model.dao.proposal.ProposalMethods;
import com.model.entities.Proposal;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.Iterator;
import java.util.List;

public class ProposalMethodsImpl implements ProposalMethods {
    private static ProposalMethodsImpl ourInstance = new ProposalMethodsImpl();

    public static ProposalMethodsImpl getInstance() {
        return ourInstance;
    }

    private ProposalMethodsImpl() {
    }

    public Proposal selectById(int id) {
        Session session = openSession();
        Query query = session.getNamedQuery("selectProposalById");
        query.setParameter("id", id);
        Iterator<Proposal> proposal = query.iterate();
        return proposal.next();
    }






    public <T> boolean deleteById(int id) {
        Session session = openSession();
        session.getTransaction().begin();
        try {
            Query query = session.getNamedQuery("deleteProposalById");
            query.setParameter("id",id);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            if(result>0)
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
    private Session openSession(){
        try {
            return HibernateUtil.getSessionFactory().openSession();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
