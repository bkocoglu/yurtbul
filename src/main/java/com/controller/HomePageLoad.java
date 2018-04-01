package com.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoadServlet" , urlPatterns = "/load")
public class HomePageLoad extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("login" , "true");
        servletContext.setAttribute("username" , "Yazilim Muhendisligi");
    }
}
