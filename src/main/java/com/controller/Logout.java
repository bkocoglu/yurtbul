package com.controller;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class Logout extends ActionSupport implements SessionAware{
    private SessionMap<String,Object> sessionMap;

    @Override
    public String execute() throws Exception {
        try{
            sessionMap.put("user",null);
        }catch (Exception e){
            e.printStackTrace();
            sessionMap.put("resultState", "Başarısız");
            sessionMap.put("resultMessage", "İşleminiz geçici bir süreliğine gerçekleştirilemiyor.");
            return ERROR;
        }
        sessionMap.put("resultState", "Başarılı");
        sessionMap.put("resultMessage", "Güvenli bir şekilde çıkış yaptınız !");
        return SUCCESS;
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap =(SessionMap) map;
    }
}
