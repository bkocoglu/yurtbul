package com.test;

import com.dao.impl.TypeDaoImpl;
import com.dao.impl.CityDaoImpl;
import com.dao.impl.GlobalDaoImpl;

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
