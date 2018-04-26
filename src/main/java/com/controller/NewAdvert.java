package com.controller;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class NewAdvert extends ActionSupport implements SessionAware {
    private SessionMap<String, Object> sessionMap;

    private String name,type,city,district,neighborhood,addressDetail,coordinateLatitude,coordinateLongitude,explanation;
    private String price,telephone;
    //private File mainPhoto,photo1,photo2,photo3,photo4,photo5,photo6,photo7;

    @Override
    public String execute() throws Exception {
        System.out.println(getName()+" "+getType()+" "+getCity()+" "+getDistrict()+" "+getNeighborhood()+" " +
                ""+getAddressDetail()+" "+getCoordinateLatitude()+" "+getCoordinateLongitude()+" "+getExplanation()+"" +
                " "+getPrice()+" "+getTelephone());
        if (getName().equals("") || getType().equals("") || getCity().equals("") || getNeighborhood().equals("") ||
                getAddressDetail().equals("") || getCoordinateLongitude().equals("") || getCoordinateLatitude().equals("") ||
                getExplanation().equals("") || getPrice().equals("") || getTelephone().equals("")){
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "Hiçbir Alanı Boş Bırakmamalısınız !");
            return ERROR;
        }
        boolean telephoneCheck = getTelephone().length()==11;
        boolean telephoneCheck2 = getTelephone().charAt(0)=='0' && getTelephone().charAt(1)=='5';
        if (!(telephoneCheck && telephoneCheck2)){
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "Girdiğiniz telefon numarası hatalı !");
            return ERROR;
        }
        return SUCCESS;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap =(SessionMap) map;
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        try {
            this.name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        try {
            this.district = new String(district.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        try {
            this.neighborhood = new String(neighborhood.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        try {
            this.addressDetail = new String(addressDetail.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getCoordinateLatitude() {
        return coordinateLatitude;
    }

    public void setCoordinateLatitude(String coordinateLatitude) {
        this.coordinateLatitude = coordinateLatitude;
    }

    public String getCoordinateLongitude() {
        return coordinateLongitude;
    }

    public void setCoordinateLongitude(String coordinateLongitude) {
        this.coordinateLongitude = coordinateLongitude;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        try {
            this.explanation = new String(explanation.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
