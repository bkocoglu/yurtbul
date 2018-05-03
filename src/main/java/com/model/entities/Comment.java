package com.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;

    @ManyToOne()
    private Advert advert;

    private String date;
    private String senderName;
    private String commentText;
    private int star;
    private boolean approval;       //onay

    public Comment() {

    }

    public Comment(Advert advert, String date, String senderName, String commentText, int star, boolean approval) {
        this.advert = advert;
        this.date = date;
        this.senderName = senderName;
        this.commentText = commentText;
        this.star = star;
        this.approval = approval;
    }

    public boolean isApproval() {
        return approval;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
