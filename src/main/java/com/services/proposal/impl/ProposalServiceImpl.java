package com.services.proposal.impl;

import com.dao.impl.GlobalDaoImpl;
import com.dao.impl.ProposalDaoImpl;
import com.model.entities.Proposal;
import com.services.proposal.ProposalService;

public class ProposalServiceImpl implements ProposalService{
    private static ProposalServiceImpl ourInstance = new ProposalServiceImpl();

    public static ProposalServiceImpl getInstance() {
        return ourInstance;
    }

    private ProposalServiceImpl() {
    }

    ProposalDaoImpl proposalDao = ProposalDaoImpl.getInstance();
    GlobalDaoImpl globalMethods = new GlobalDaoImpl();

    public boolean sendProposal(Proposal proposal) {
        return globalMethods.save(proposal);
    }

    public Proposal getProposalById(int id) {
        return proposalDao.selectById(id);
    }

    public boolean deleteProposalById(int id) {
        return proposalDao.deleteById(id);
    }

}
