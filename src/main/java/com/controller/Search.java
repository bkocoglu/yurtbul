package com.controller;

import com.model.dao.global.impl.GlobalDaoImpl;
import com.model.entities.Advert;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

public class Search extends ActionSupport implements SessionAware,ServletContextAware {
    private SessionMap<String,Object> sessionMap;
    ServletContext context;

    private String searchText;

    @Override
    public String execute() throws Exception {
        try {
            if (searchText.equals("") || searchText==null){
                sessionMap.put("filterResultList",null);
                sessionMap.put("resultState", "Başarısız");
                sessionMap.put("resultMessage", "Aranacak kelimeyi giriniz.");
                return ERROR;
            }
            GlobalDaoImpl globalDao = new GlobalDaoImpl();

            List<Advert> allAdverts = globalDao.getAllData(Advert.class);
            System.out.println(allAdverts);

            if (allAdverts == null){
                sessionMap.put("filterResultList",null);
                sessionMap.put("resultState", "Başarısız");
                sessionMap.put("resultMessage", "Sistem geçici bir süreliğine hizmet veremiyor.");
                return ERROR;
            }
            if (allAdverts.isEmpty()){
                sessionMap.put("filterResultList",null);
                sessionMap.put("resultState", "Başarısız");
                sessionMap.put("resultMessage", "Sistemde henüz kayıtlı mekan yok.");
                return ERROR;
            }
            String searchStringLower = searchText.toLowerCase();

            for (int i=0;i<allAdverts.size();i++){
                String advertString = allAdverts.get(i).toString().toLowerCase();
                if (advertString.lastIndexOf(searchStringLower)==-1){
                    allAdverts.remove(i);
                    i--;
                }
            }

            if (allAdverts.isEmpty()){
                sessionMap.put("filterResultList",allAdverts);
                sessionMap.put("resultState", "Başarılı");
                sessionMap.put("resultMessage", "Aradığınız kriterlere uygun mekan bulunamadı.");
                return SUCCESS;
            }

            sessionMap.put("filterResultList",allAdverts);
            sessionMap.put("resultState", "Başarılı");
            sessionMap.put("resultMessage", "Arama Sonuçları");
            return SUCCESS;


        }catch (Exception e){
            e.printStackTrace();
            sessionMap.put("filterResultList",null);
            sessionMap.put("resultState", "Başarısız");
            sessionMap.put("resultMessage", "Sistem geçici bir süreliğine hizmet veremiyor.");
            return ERROR;
        }
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap =(SessionMap) map;
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }
}
