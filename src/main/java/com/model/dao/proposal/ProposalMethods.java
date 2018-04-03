package com.model.dao.proposal;

import com.model.entities.Proposal;

import java.util.List;

public interface ProposalMethods {
    public Proposal selectById(int id);

    public <T> boolean deleteById(int id);

}
