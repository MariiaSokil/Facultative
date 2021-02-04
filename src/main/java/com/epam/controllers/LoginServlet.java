package com.epam.controllers;

import com.epam.model.User;
import com.epam.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserService userService;

    public LoginServlet() {
        this.userService = new UserService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("you are logged in");
        System.out.println(request.getParameter("uname"));
        System.out.println(request.getParameter("psw"));

        User user = new User();
        user.setLogin(request.getParameter("uname"));
        user.setPassword(request.getParameter("psw"));


        if(userService.isValid(user)){
            HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser",userService.getByLogin(user.getLogin()));
            response.sendRedirect("userLogged.jsp"); //logged-in page

          } else {
            response.sendRedirect("invalid.jsp"); //error page
          }

    }
}