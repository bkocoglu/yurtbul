package com.model.dao.proposal;

import com.model.entities.Proposal;

import java.util.List;

public interface ProposalDao {
    public Proposal selectById(int id);

    public boolean save(Object entity);

    public boolean delete(Object entity);

    public <T> List<T> getAllData(Class<T> entity);

    public <T> boolean deleteById(int id);

}
