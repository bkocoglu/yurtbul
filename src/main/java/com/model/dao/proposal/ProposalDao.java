package com.model.dao.proposal;

import com.model.entities.Proposal;

import java.util.List;

public interface ProposalDao {
    public Proposal selectById(int id);

    public <T> boolean deleteById(int id);

}
