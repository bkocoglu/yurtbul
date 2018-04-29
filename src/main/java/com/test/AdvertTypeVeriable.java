package com.test;

import com.model.dao.advertType.impl.TypeDaoImpl;
import com.model.dao.city.impl.CityDaoImpl;
import com.model.dao.global.impl.GlobalDaoImpl;
import com.model.entities.AdvertType;

public class AdvertTypeVeriable {
    public static void main(String [] args){
        //AdvertType advertType = new AdvertType("yurt");
        //AdvertType advertType1 = new AdvertType("apart");

        GlobalDaoImpl globalDao = new GlobalDaoImpl();
        TypeDaoImpl typeDao = new TypeDaoImpl();
        CityDaoImpl cityDao = new CityDaoImpl();

        System.out.println(typeDao.findByName("yurt"));
        System.out.println(typeDao.findByName("apart"));
        System.out.println(cityDao.findByName("Istanbul"));

        //globalDao.save(advertType);
        //globalDao.save(advertType1);
    }
}
