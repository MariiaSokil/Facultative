package com.epam.controllers;

import com.epam.model.User;
import com.epam.service.UserService;

import javax.servlet.RequestDispatcher;
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
        User u = userService.getByLogin(request.getParameter("uname"));
        if(userService.isValid(u, request.getParameter("psw"))){
            System.out.println("User authenticated!");
            HttpSession session = request.getSession(true);
            request.setAttribute("user", u);

            request.setAttribute("courses", u.getCourses());


            RequestDispatcher rd = getServletContext().getRequestDispatcher("/student.jsp");
            rd.forward(request, response);
          } else {
            response.sendRedirect("invalid.jsp"); //error page
          }

    }
}