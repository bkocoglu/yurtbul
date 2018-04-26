package com.controller;


import com.model.dao.global.impl.GlobalDaoImpl;
import com.model.entities.City;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "LoadServlet" , urlPatterns = "/load")
public class HomePageLoad extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ServletContext servletContext = getServletContext();

        GlobalDaoImpl globalDao = new GlobalDaoImpl();


        List<City> cityList = globalDao.getAllData(City.class);
        servletContext.setAttribute("allCity", cityList);
        System.out.println("load servlet calisti");
    }
}
