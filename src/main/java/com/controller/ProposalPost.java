package com.controller;

import com.model.entities.Proposal;
import com.services.proposal.impl.ProposalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProposalPost extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean eMailCheck = req.getParameter("eMailCheck") != null;
        ProposalServiceImpl proposalService = ProposalServiceImpl.getInstance();


        if(proposalService.sendProposal(req.getParameter("eMail"), req.getParameter("proposalText"), eMailCheck))
            System.out.println("Ekleme İşlem Başarılı.");
        else
            System.out.println("Ekleme İşlem Başarısız.");
    }
}
