package com.services.proposal.impl;

import com.model.dao.proposal.impl.ProposalDaoImpl;
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

    public boolean sendProposal(String email, String proposalText, boolean eMailCheck) {
        Proposal proposal = new Proposal(email, proposalText, eMailCheck);
        return proposalDao.save(proposal);
    }

    public Proposal getProposalById(int id) {
        return proposalDao.selectById(id);
    }

    public boolean deleteProposalById(int id) {
        return proposalDao.deleteById(id);
    }

}
