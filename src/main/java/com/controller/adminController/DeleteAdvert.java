package com.controller.adminController;

import com.model.dao.advert.impl.AdvertDaoImpl;
import com.model.dao.comment.impl.CommentDaoImpl;
import com.model.dao.global.impl.GlobalDaoImpl;
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

public class DeleteAdvert extends ActionSupport implements ServletContextAware, SessionAware {
    ServletContext context;
    private SessionMap<String, Object> sessionMap;

    @Override
    public String execute() throws Exception {
        System.out.println(context.getAttribute("advertList"));
        System.out.println(ServletActionContext.getRequest().getParameter("id"));

        AdvertDaoImpl advertDao = new AdvertDaoImpl();
        ImagesDaoImpl imagesDao = new ImagesDaoImpl();
        GlobalDaoImpl globalDao = new GlobalDaoImpl();
        CommentDaoImpl commentDao = new CommentDaoImpl();

        try {
            String idString = ServletActionContext.getRequest().getParameter("id");
            int id = Integer.parseInt(idString);

            Advert advert = advertDao.findById(id);
            System.out.println(advert.toString());

            if (advert==null){
                sessionMap.put("resultState", "Başarsız");
                sessionMap.put("resultMessage", "İşleminiz şuanda gerçekleştirilemiyor.");
                return ERROR;
            }

            List<Images> imagesList = imagesDao.findByAdvert(advert);
            System.out.println(imagesList);

            if (imagesList==null){
                sessionMap.put("resultState", "Başarsız");
                sessionMap.put("resultMessage", "İşleminiz şuanda gerçekleştirilemiyor.");
                return ERROR;
            }

            if (!imagesList.isEmpty()){
                for (Images images:imagesList) {
                    globalDao.delete(images);
                }
            }

            List<Comment> commentList = commentDao.findByAdvert(advert);

            if(commentList == null){
                sessionMap.put("resultState", "Başarsız");
                sessionMap.put("resultMessage", "İşleminiz şuanda gerçekleştirilemiyor.");
                return ERROR;
            }

            List<Comment> commentList1 = (List<Comment>) context.getAttribute("pendingComments");

            if (!commentList.isEmpty()){
                for (Comment comment:commentList) {
                    globalDao.delete(comment);
                    for (Comment comment1: commentList1) {
                        if (comment1.getCommentId() == comment.getCommentId()){
                            commentList1.remove(comment1);
                            break;
                        }
                    }

                }
            }

            context.setAttribute("pendingComments",commentList1);

            globalDao.delete(advert);

            List<Advert> advertList = (List<Advert>) context.getAttribute("advertList");

            List<Advert> showCaseList = (List<Advert>) context.getAttribute("showCaseList");

            if (!advertList.isEmpty()){
                for (Advert advertItem: advertList) {
                    if (advertItem.getId()==advert.getId()){
                        System.out.println("eşleşti");
                        advertList.remove(advertItem);
                        break;
                    }
                }

                for (Advert showAdvertItem : showCaseList){
                    if (showAdvertItem.getId()==advert.getId()){
                        showCaseList.remove(showAdvertItem);
                        break;
                    }
                }

            }
            context.setAttribute("advertList",advertList);

            context.setAttribute("showCaseList",showCaseList);



        }catch (Exception e){
            e.printStackTrace();
        }

        sessionMap.put("resultState", "Başarılı");
        sessionMap.put("resultMessage", "Silme işlemi başarıyla gerçekleşti.");
        return SUCCESS;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap =(SessionMap) map;
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }
}
