package com.controller;

import com.model.entities.User;
import com.opensymphony.xwork2.ActionSupport;
import com.services.user.impl.NewUserServiceImpl;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class NewUser extends ActionSupport implements SessionAware {
    private String newName, newLastName, newEmail, newPass, newPassAgain;

    private SessionMap<String, Object> sessionMap;

    @Override
    public String execute() throws Exception {
        NewUserServiceImpl newUserService = new NewUserServiceImpl();

        if (getNewEmail().equals("") || getNewLastName().equals("") || getNewName().equals("")
                || getNewPass().equals("") || getNewPassAgain().equals("") ){
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "Hiçbir Alanı Boş Bırakmamalısınız !");
            return ERROR;
        }
        if (getNewEmail().length()<5 || getNewPass().length()<5){
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "Kullanıcı Adınız ve Şifreniz 5 Karakterden Uzun Olmalı !");
            return ERROR;
        }
        if (!getNewPass().equals(getNewPassAgain())){
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "Şifreleriniz Eşleşmedi Lütfen Kontrol Ediniz !");
            return ERROR;
        }
        if (getNewName().equals(getNewLastName())){
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "Adınız ve Soyadınızın Aynı Olduğuna Emin Misiniz ?");
            return ERROR;
        }

        User user = new User(getNewName(), getNewLastName(), getNewEmail(), getNewPass());

        boolean res = newUserService.createNewUser(user);

        if (res) {
            sessionMap.put("resultState", "Başarılı");
            sessionMap.put("resultMessage", "Kayıt Başarıyla Oluşturuldu.");
            return SUCCESS;
        } else {
            sessionMap.put("resultMessage", "İşlem Başarısız");
            return ERROR;
        }
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap =(SessionMap) map;

    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getNewPassAgain() {
        return newPassAgain;
    }

    public void setNewPassAgain(String newPassAgain) {
        this.newPassAgain = newPassAgain;
    }


}
