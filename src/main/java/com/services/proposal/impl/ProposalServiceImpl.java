package com.services.proposal.impl;

import com.model.dao.global.GlobalMethods;
import com.model.dao.global.impl.GlobalMethodsImpl;
import com.model.dao.proposal.impl.ProposalMethodsImpl;
import com.model.entities.Proposal;
import com.services.proposal.ProposalService;

public class ProposalServiceImpl implements ProposalService{
    private static ProposalServiceImpl ourInstance = new ProposalServiceImpl();

    public static ProposalServiceImpl getInstance() {
        return ourInstance;
    }

    private ProposalServiceImpl() {
    }

    ProposalMethodsImpl proposalDao = ProposalMethodsImpl.getInstance();
    GlobalMethodsImpl globalMethods = new GlobalMethodsImpl();

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
