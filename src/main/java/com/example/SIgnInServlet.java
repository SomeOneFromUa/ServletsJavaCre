package com.example;

import signINUP.DataUsers;
import signINUP.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "signIn", urlPatterns = "/signin" )
public class SIgnInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User(DataUsers.connect());
        user.SetUser(login, password);
        boolean flag = user.signIn();
        if(flag){
            req.setAttribute("name", user.getName());
            getServletContext().getRequestDispatcher("/success.jsp").forward(req, resp);
        } else  getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        user.close();
    }
}
