package com.controller.indexController;

import com.dao.impl.AdvertDaoImpl;
import com.dao.impl.TypeDaoImpl;
import com.dao.impl.CityDaoImpl;
import com.model.entities.Advert;
import com.model.entities.AdvertType;
import com.model.entities.City;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AdvertFilter extends ActionSupport implements SessionAware{
    private String cityId;
    private String gridRadios;
    private String priceRange;

    private SessionMap<String, Object> sessionMap;

    @Override
    public String execute() throws Exception {
        try{
            System.out.println(cityId + " " + gridRadios + " " + priceRange);

            CityDaoImpl cityDao = new CityDaoImpl();
            TypeDaoImpl typeDao = new TypeDaoImpl();
            AdvertDaoImpl advertDao = new AdvertDaoImpl();

            City city = cityDao.findById(Integer.parseInt(cityId));

            if (city==null){
                sessionMap.put("filterResultList",null);
                sessionMap.put("resultState", "Başarısız");
                sessionMap.put("resultMessage", "Sistem geçici bir süreliğine hizmet veremiyor. Lütfen daha sonra tekrar deneyin.");
                return ERROR;
            }
            List<Advert> filterResult = new LinkedList<Advert>();

            if(gridRadios.equals("apart") || gridRadios.equals("yurt")){
                AdvertType advertType = typeDao.findByName(gridRadios);
                filterResult = advertDao.filters(city,advertType);
            }else {
                filterResult = advertDao.filters(city);
            }

            if (filterResult==null){
                sessionMap.put("filterResultList",null);
                sessionMap.put("resultState", "Başarısız");
                sessionMap.put("resultMessage", "Sistem geçici bir süreliğine hizmet veremiyor.");
                return ERROR;
            }

            int priceRangeInt = Integer.parseInt(priceRange);

            if (filterResult.size()>0) {
                switch (priceRangeInt) {
                    case 1: {
                        for (int i = 0 ; i < filterResult.size() ; i++) {
                            if (filterResult.get(i).getPrice() > 400) {
                                filterResult.remove(filterResult.get(i));
                                i--;
                            }
                        }
                        break;
                    }
                    case 2: {
                        for (int i = 0 ; i < filterResult.size() ; i++) {
                            if (filterResult.get(i).getPrice() < 400 || filterResult.get(i).getPrice() > 600) {
                                filterResult.remove(filterResult.get(i));
                                i--;
                            }
                        }
                        break;
                    }
                    case 3: {
                        for (int i = 0 ; i < filterResult.size() ; i++) {
                            if (filterResult.get(i).getPrice() < 600 || filterResult.get(i).getPrice() > 800) {
                                filterResult.remove(filterResult.get(i));
                                i--;
                            }
                        }
                        break;
                    }
                    case 4: {
                        for (int i = 0 ; i < filterResult.size() ; i++) {
                            if (filterResult.get(i).getPrice() < 800) {
                                filterResult.remove(filterResult.get(i));
                                i--;
                            }
                        }
                        break;
                    }
                }
            }

            if (filterResult.isEmpty()){
                sessionMap.put("filterResultList",filterResult);
                sessionMap.put("resultState", "Başarılı");
                sessionMap.put("resultMessage", "Aradığınız kriterlere uygun mekan bulunamadı.");
                return SUCCESS;
            }

            sessionMap.put("filterResultList",filterResult);
            sessionMap.put("resultState", "Başarılı");
            sessionMap.put("resultMessage", "Arama Sonuçları");
            return SUCCESS;

        }catch (Exception e){
            e.printStackTrace();
            sessionMap.put("filterResultList",null);
            sessionMap.put("resultState", "Başarısız");
            sessionMap.put("resultMessage", "Sistem geçici bir süreliğine hizmet veremiyor. Lütfen daha sonra tekrar deneyin.");
            return ERROR;
        }
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getGridRadios() {
        return gridRadios;
    }

    public void setGridRadios(String gridRadios) {
        this.gridRadios = gridRadios;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap =(SessionMap) map;
    }
}
