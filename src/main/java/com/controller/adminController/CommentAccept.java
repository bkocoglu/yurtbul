package com.controller.adminController;

import com.dao.impl.AdvertDaoImpl;
import com.dao.impl.CommentDaoImpl;
import com.dao.impl.GlobalDaoImpl;
import com.model.entities.Advert;
import com.model.entities.Comment;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

public class CommentAccept extends ActionSupport implements SessionAware,ServletContextAware {
    ServletContext context;
    private SessionMap<String, Object> sessionMap;

    @Override
    public String execute() throws Exception {
        try {
            CommentDaoImpl commentDao = new CommentDaoImpl();
            AdvertDaoImpl advertDao = new AdvertDaoImpl();
            GlobalDaoImpl globalDao = new GlobalDaoImpl();

            String islem =  ServletActionContext.getRequest().getParameter("islem");
            String commentId =  ServletActionContext.getRequest().getParameter("commentId");

            System.out.println(islem + " " + commentId);

            if (islem==null || islem.equals("") || commentId==null || commentId.equals("")){
                sessionMap.put("resultState", "Başarsız");
                sessionMap.put("resultMessage", "Yanlış parametre gönderildi.");
                return ERROR;
            }
            Comment comment = commentDao.findById(Integer.parseInt(commentId));

            if (comment==null){
                sessionMap.put("resultState", "Başarsız");
                sessionMap.put("resultMessage", "Yanlış parametre gönderildi.");
                return ERROR;
            }

            if (islem.equals("ekle")){
                comment.setApproval(true);
                boolean result = globalDao.update(comment);
                if (!result){
                    sessionMap.put("resultState", "Başarsız");
                    sessionMap.put("resultMessage", "İşleminiz şuanda gerçekleştirilemiyor.");
                    return ERROR;
                }
                Advert advert = comment.getAdvert();

                List<Integer> starList = commentDao.getStars(advert);
                System.out.println(starList);
                if (!starList.isEmpty()){
                    int total = 0;
                    for (int star: starList)
                        total += star;

                    System.out.println("total " + total);

                    System.out.println("yorum sayisi " +starList.size()  );

                    float newStar = (float) total/starList.size();
                    System.out.println(newStar);

                    advert.setStar(newStar);
                    boolean advertUpdateResult = globalDao.update(advert);
                    if (!advertUpdateResult){
                        sessionMap.put("resultState", "Başarsız");
                        sessionMap.put("resultMessage", "İşleminiz şuanda gerçekleştirilemiyor.");
                        return ERROR;
                    }
                    List<Advert> advertList = (List<Advert>) context.getAttribute("advertList");

                    if (!advertList.isEmpty()){
                        for (Advert advertItem : advertList){
                            if (advertItem.getId()==advert.getId()){
                                advertList.remove(advertItem);
                                advertList.add(advert);
                                break;
                            }
                        }
                    }
                    context.setAttribute("advertList",advertList);

                    List<Advert> showCaseList = (List<Advert>) context.getAttribute("showCaseList");
                    if (!showCaseList.isEmpty()){
                        for (Advert showAdvertItem : showCaseList){
                            if (showAdvertItem.getId()==advert.getId()){
                                showCaseList.remove(showAdvertItem);
                                showCaseList.add(advert);
                                break;
                            }
                        }
                    }
                    context.setAttribute("showCaseList",showCaseList);

                    List<Comment> commentList = (List<Comment>) context.getAttribute("pendingComments");

                    if (!commentList.isEmpty()){
                        for (Comment commentItem:commentList){
                            if (commentItem.getCommentId()==comment.getCommentId()){
                                commentList.remove(commentItem);
                                break;
                            }
                        }
                    }

                    context.setAttribute("pendingComments",commentList);
                }
            }
            if (islem.equals("sil")){
                boolean commentDeleteResult = globalDao.delete(comment);
                if (!commentDeleteResult){
                    sessionMap.put("resultState", "Başarsız");
                    sessionMap.put("resultMessage", "İşleminiz şuanda gerçekleştirilemiyor.");
                    return ERROR;
                }
                List<Comment> commentList = (List<Comment>) context.getAttribute("pendingComments");

                if (!commentList.isEmpty()){
                    for (Comment commentItem:commentList){
                        if (commentItem.getCommentId()==comment.getCommentId()){
                            commentList.remove(commentItem);
                            break;
                        }
                    }
                }

                context.setAttribute("pendingComments",commentList);
            }
            return SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "İşleminiz şuanda gerçekleştirilemiyor.");
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
