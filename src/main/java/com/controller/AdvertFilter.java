package com.controller;

import com.opensymphony.xwork2.ActionSupport;

public class AdvertFilter extends ActionSupport {
    private String cityName;
    private String gridRadios;
    private String priceRange;

    @Override
    public String execute() throws Exception {
        System.out.println(cityName + " " + gridRadios + " " + priceRange);

        return SUCCESS;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
}
