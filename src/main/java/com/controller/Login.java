package com.controller;

import com.model.dao.advert.impl.AdvertDaoImpl;
import com.model.dao.comment.impl.CommentDaoImpl;
import com.model.dao.user.impl.userDaoImpl;
import com.model.entities.Advert;
import com.model.entities.Comment;
import com.model.entities.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Login extends ActionSupport implements SessionAware, ServletContextAware{
    private String loginName, loginPassword;
    private SessionMap<String, Object> sessionMap;
    ServletContext context;

    @Override
    public String execute() throws Exception {

        // VALİDATİON

        if (getLoginName().equals("") || getLoginPassword().equals("")){
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "Kullanıcı adı ve şifreyi boş bırakamazsınız !");
            return ERROR;
        }

        userDaoImpl userDaoImpl = new userDaoImpl();
        User user = userDaoImpl.logCheck(getLoginName(),getLoginPassword());
        if(user==null){
            sessionMap.put("resultState", "Başarısız");
            sessionMap.put("resultMessage", "Kullanıcı adı ve şifre hatalı !");
            return ERROR;
        }else {
            AdvertDaoImpl advertDao = new AdvertDaoImpl();
            List<Advert> advertList = advertDao.findByUser(user);
            System.out.println(advertList);
            ServletContext servletContext = ServletActionContext.getServletContext();
            if (advertList.isEmpty()){
                System.out.println("İlan yok");
                context.setAttribute("commentCount","0");
            }else {
                context.setAttribute("advertList",advertList);
                CommentDaoImpl commentDao = new CommentDaoImpl();
                List<Comment> allPendingComment = new LinkedList<Comment>();
                for (Advert advertItem: advertList) {
                    List<Comment> commentList = commentDao.getPendingComment(advertItem);
                    if (!commentList.isEmpty()){
                        for (Comment commentItem: commentList) {
                            allPendingComment.add(commentItem);
                        }
                    }
                }
                if (allPendingComment.isEmpty()){
                    context.setAttribute("commentCount","0");
                }else {
                    context.setAttribute("commentCount",allPendingComment.size());
                    context.setAttribute("pendingComments",allPendingComment);
                    System.out.println(allPendingComment);
                }
            }

            sessionMap.put("resultState", "Başarılı");
            sessionMap.put("resultMessage", "Tebrikler başarılı bir şekilde giriş yaptınız !");
            sessionMap.put("user",user);
            return SUCCESS;
        }
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap =(SessionMap) map;
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }
}
