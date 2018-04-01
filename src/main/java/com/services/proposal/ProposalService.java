package com.services.proposal;

import com.model.entities.Proposal;

public interface ProposalService {

    public boolean sendProposal(String email, String proposalText, boolean eMailCheck);

    public Proposal getProposalById(int id);

    public boolean deleteProposalById(int id);

}
