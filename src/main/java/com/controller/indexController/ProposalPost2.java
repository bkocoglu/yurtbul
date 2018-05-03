package com.controller.indexController;

import com.model.entities.Proposal;
import com.opensymphony.xwork2.ActionSupport;
import com.services.proposal.impl.ProposalServiceImpl;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class ProposalPost2 extends ActionSupport implements SessionAware {
    private String email, proposalText;
    private boolean emailcheck;
    private SessionMap<String, Object> sessionMap;

    @Override
    public String execute() throws Exception {
        ProposalServiceImpl proposalService = ProposalServiceImpl.getInstance();

        Proposal proposal = new Proposal(email,proposalText,emailcheck);

        if(proposalService.sendProposal(proposal)){
            getSessionMap().put("resultState","Başarılı");
            getSessionMap().put("resultMessage","Öneriniz Tarafımıza İletildi. Teşekkürler.");
            return SUCCESS;
        }
        else{
            return SUCCESS;
        }

    }

    public void setSession(Map<String, Object> map) {
        sessionMap =(SessionMap) map;
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProposalText() {
        return proposalText;
    }

    public void setProposalText(String proposalText) {
        this.proposalText = proposalText;
    }

    public boolean isEmailcheck() {
        return emailcheck;
    }

    public void setEmailcheck(boolean emailcheck) {
        this.emailcheck = emailcheck;
    }
}
