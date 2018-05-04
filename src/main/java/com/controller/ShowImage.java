package com.controller;

import com.dao.impl.AdvertDaoImpl;
import com.dao.impl.ImagesDaoImpl;
import com.model.entities.Advert;
import com.model.entities.Images;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

public class ShowImage extends ActionSupport {
    private byte[] itemImage;

    public String execute() {
        try{
            ImagesDaoImpl imagesDao = new ImagesDaoImpl();
            AdvertDaoImpl advertDao = new AdvertDaoImpl();

            String advertIdString = ServletActionContext.getRequest().getParameter("advertId");

            String imageId = ServletActionContext.getRequest().getParameter("imageId");

            Images images;
            if (imageId == null){
                Advert advert = advertDao.findById(Integer.parseInt(advertIdString));

                images = imagesDao.getMainImages(advert);
            }else {
                images = imagesDao.findById(Integer.parseInt(imageId));
            }


            HttpServletResponse response = ServletActionContext.getResponse();
            response.reset();
            response.setContentType("multipart/form-data");
            itemImage = images.getImage();
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(itemImage);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public byte[] getItemImage() {
        return itemImage;
    }

    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }
}
