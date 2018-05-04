package com.controller.showAdvert;

import com.dao.impl.AdvertDaoImpl;
import com.dao.impl.GlobalDaoImpl;
import com.model.entities.Advert;
import com.model.entities.Comment;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class PostComment extends ActionSupport implements SessionAware, ServletContextAware {
    private SessionMap<String, Object> sessionMap;
    ServletContext context;

    private String senderName;
    private String commentText;
    private String commentStar;
    private String advertId;

    @Override
    public String execute() throws Exception {
        GlobalDaoImpl globalDao = new GlobalDaoImpl();
        AdvertDaoImpl advertDao = new AdvertDaoImpl();
        try {
            System.out.println(senderName + " " + commentText + " " + commentStar + " " + advertId);
            if (senderName==null || senderName.equals("") || commentText==null || commentText.equals("") || commentStar==null ||
                    commentStar.equals("") || advertId==null || advertId.equals("")){
                sessionMap.put("commentMessage","Yorum yapabilmeniz için bilgileri eksiksiz doldurmanız gerekir.");
                return ERROR;
            }

            Advert advert = advertDao.findById(Integer.parseInt(advertId));

            if (advert==null){
                sessionMap.put("commentMessage", "Geçersiz parametre gönderildi.");
                return ERROR;
            }

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            String date = formatter.format(new Date());
            System.out.println(date);

            Comment comment = new Comment();

            comment.setAdvert(advert);
            comment.setDate(date);
            comment.setSenderName(senderName);
            comment.setCommentText(commentText);
            comment.setStar(Integer.parseInt(commentStar));
            comment.setApproval(false);

            boolean result = globalDao.save(comment);

            if (result){
                sessionMap.put("commentMessage","Yorumunuz başarılı bir şekilde alındı. Teşekkür ederiz.");
                return SUCCESS;
            }else {
                sessionMap.put("commentMessage","İşlem geçici bir süreliğine gerçekleştirilemiyor.");
                return ERROR;
            }

        }catch (Exception e){
            e.printStackTrace();
            sessionMap.put("commentMessage","İşlem geçici bir süreliğine gerçekleştirilemiyor.");
            return ERROR;
        }
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public ServletContext getContext() {
        return context;
    }

    public void setContext(ServletContext context) {
        this.context = context;
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

    public String getCommentStar() {
        return commentStar;
    }

    public void setCommentStar(String commentStar) {
        this.commentStar = commentStar;
    }

    public String getAdvertId() {
        return advertId;
    }

    public void setAdvertId(String advertId) {
        this.advertId = advertId;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap =(SessionMap) map;
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }
}
