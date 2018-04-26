package com.test;

import com.model.dao.city.impl.CityDaoImpl;
import com.model.dao.global.impl.GlobalDaoImpl;
import com.model.entities.City;

public class CityTest {
    public static void main(String[]args){
        /*City city = new City(1,"deneme");
        GlobalDaoImpl globalDao = new GlobalDaoImpl();
        globalDao.save(city);
    */
        GlobalDaoImpl globalDao = new GlobalDaoImpl();
        CityDaoImpl cityDao = new CityDaoImpl();
        System.out.println(cityDao.findById(34).toString());

        System.out.println(globalDao.getAllData(City.class));
    }
}
