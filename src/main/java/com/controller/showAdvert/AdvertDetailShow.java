package com.controller.showAdvert;

import com.model.dao.advert.impl.AdvertDaoImpl;
import com.model.dao.comment.impl.CommentDaoImpl;
import com.model.dao.images.impl.ImagesDaoImpl;
import com.model.entities.Advert;
import com.model.entities.Comment;
import com.model.entities.Images;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

public class AdvertDetailShow extends ActionSupport implements SessionAware,ServletContextAware {
    private SessionMap<String, Object> sessionMap;
    ServletContext context;

    @Override
    public String execute() throws Exception {
        try{
            AdvertDaoImpl advertDao = new AdvertDaoImpl();
            ImagesDaoImpl imagesDao = new ImagesDaoImpl();
            CommentDaoImpl commentDao = new CommentDaoImpl();

            String advertId = ServletActionContext.getRequest().getParameter("advertId");

            Advert advert = advertDao.findById(Integer.parseInt(advertId));
            System.out.println(advert);
            if (advert==null){
                context.setAttribute("commentList",null);
                context.setAttribute("imageIdList", null);
                context.setAttribute("selectedAdvert",null);
                sessionMap.put("resultState", "Başarılı");
                sessionMap.put("resultMessage", "Sistem geçici bir süreliğine hizmet veremiyor.");
                return ERROR;
            }

            List<Integer> imageIdList = imagesDao.getImagesId(advert);

            if (imageIdList==null){
                context.setAttribute("commentList",null);
                context.setAttribute("imageIdList", null);
                context.setAttribute("selectedAdvert",null);
                sessionMap.put("resultState", "Başarılı");
                sessionMap.put("resultMessage", "Sistem geçici bir süreliğine hizmet veremiyor.");
                return ERROR;
            }

            List<Comment> commentList = commentDao.getNotifiedComment(advert);

            if (commentList==null){
                context.setAttribute("commentList",null);
                context.setAttribute("imageIdList", null);
                context.setAttribute("selectedAdvert",null);
                sessionMap.put("resultState", "Başarılı");
                sessionMap.put("resultMessage", "Sistem geçici bir süreliğine hizmet veremiyor.");
                return ERROR;
            }

            context.setAttribute("commentList",commentList);
            context.setAttribute("imageIdList", imageIdList);
            context.setAttribute("selectedAdvert",advert);
            return SUCCESS;

        }catch (Exception e){
            e.printStackTrace();
            context.setAttribute("commentList",null);
            context.setAttribute("imageIdList", null);
            context.setAttribute("selectedAdvert",null);
            sessionMap.put("resultState","Başarısız");
            sessionMap.put("resultMessage", "Sistem geçici bir süreliğine hizmet veremiyor.");
            return ERROR;
        }
    }

    public void setSession(Map<String, Object> map) {
        sessionMap =(SessionMap) map;
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }
}
