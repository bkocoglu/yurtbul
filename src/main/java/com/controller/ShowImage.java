package com.controller;

import com.model.dao.images.impl.ImagesDaoImpl;
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
            Images images = imagesDao.findById(10);
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
