package com.test;

import com.model.dao.global.impl.GlobalDaoImpl;
import com.model.entities.AdvertType;

public class AdvertTypeVeriable {
    public static void main(String [] args){
        AdvertType advertType = new AdvertType("yurt");
        AdvertType advertType1 = new AdvertType("apart");

        GlobalDaoImpl globalDao = new GlobalDaoImpl();

        globalDao.save(advertType);
        globalDao.save(advertType1);
    }
}
