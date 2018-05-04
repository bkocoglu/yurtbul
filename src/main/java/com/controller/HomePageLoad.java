package com.controller;


import com.dao.impl.GlobalDaoImpl;
import com.dao.impl.ImagesDaoImpl;
import com.model.entities.Advert;
import com.model.entities.City;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadServlet" , urlPatterns = "/load")
public class HomePageLoad extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ServletContext servletContext = getServletContext();

        GlobalDaoImpl globalDao = new GlobalDaoImpl();
        ImagesDaoImpl imageDao = new ImagesDaoImpl();

        //  CİTY SETTİNG

        List<City> cityList = globalDao.getAllData(City.class);
        servletContext.setAttribute("allCity", cityList);
        System.out.println("load servlet calisti");

        //  SHOWCASE SETTİNG

        List<Advert> advertList = globalDao.getAllData(Advert.class);

        if (advertList==null){
            session.setAttribute("resultState", "Başarısız");
            session.setAttribute("resultMessage", "Sistem Geçici Bir süreliğine hizmet veremiyor.");
            return;
        }
        if (advertList.isEmpty()){
            session.setAttribute("resultState", "Başarısız");
            session.setAttribute("resultMessage", "Sistemde kayıtlı mekan bulunmamaktadır.");
            return;
        }
        servletContext.setAttribute("showCaseList",advertList);
        return;


    }
}
