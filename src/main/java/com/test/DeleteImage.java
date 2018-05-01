package com.test;

import com.model.dao.global.impl.GlobalDaoImpl;
import com.model.dao.images.impl.ImagesDaoImpl;
import com.model.entities.Images;

public class DeleteImage {
    public static void main(String[]args){
        ImagesDaoImpl dao = new ImagesDaoImpl();
        GlobalDaoImpl globalDao = new GlobalDaoImpl();

        Images image = dao.findById(38);
        System.out.println(image.toString());

        globalDao.delete(image);
    }
}
