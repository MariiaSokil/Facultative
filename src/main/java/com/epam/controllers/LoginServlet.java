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
        User user = new User();
        user.setLogin(request.getParameter("uname"));
        user.setPassword(request.getParameter("psw"));


        if(userService.isValid(user)){
            System.out.println("User found!");
            HttpSession session = request.getSession(true);
            User u = userService.getByLogin(user.getLogin());
            System.out.println(u.getFirstName() + " " + u.getLastName());
            request.setAttribute("user-record", u);
            response.sendRedirect("student.jsp"); //logged-in page

          } else {
            response.sendRedirect("invalid.jsp"); //error page
          }

    }
}