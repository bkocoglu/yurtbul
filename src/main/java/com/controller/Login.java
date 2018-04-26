package com.controller;

import com.model.dao.user.impl.UserDaoİmpl;
import com.model.entities.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

public class Login extends ActionSupport implements SessionAware{
    private String loginName, loginPassword;
    private SessionMap<String, Object> sessionMap;


    @Override
    public String execute() throws Exception {
        if (getLoginName().equals("") || getLoginPassword().equals("")){
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "Kullanıcı adı ve şifreyi boş bırakamazsınız !");
            return ERROR;
        }
        UserDaoİmpl userDaoİmpl = new UserDaoİmpl();
        User user = userDaoİmpl.logCheck(getLoginName(),getLoginPassword());
        if(user==null){
            sessionMap.put("resultState", "Başarısız");
            sessionMap.put("resultMessage", "Kullanıcı adı ve şifre hatalı !");
            return ERROR;
        }else {
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
}
