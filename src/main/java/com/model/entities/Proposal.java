package com.model.entities;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "selectProposalById",
                query = "from Proposal p where p.id = :id"
        ),
        @NamedQuery(
                name = "deleteProposalById",
                query = "delete Proposal p where p.id = :id"
        )
})

@Entity
@Table(name = "proposal")
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email, proposalText;
    private boolean ePostaCheck;

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", proposalText='" + proposalText + '\'' +
                ", ePostaCheck=" + ePostaCheck +
                '}';
    }

    public Proposal() {
    }

    public Proposal(String email, String proposalText, boolean ePostaCheck) {

        this.email = email;
        this.proposalText = proposalText;
        this.ePostaCheck = ePostaCheck;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column
    public String getProposalText() {
        return proposalText;
    }

    public void setProposalText(String proposalText) {
        this.proposalText = proposalText;
    }
    @Column
    public boolean isePostaCheck() {
        return ePostaCheck;
    }

    public void setePostaCheck(boolean ePostaCheck) {
        this.ePostaCheck = ePostaCheck;
    }
}
