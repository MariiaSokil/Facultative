package com.epam.controllers;

import com.epam.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = "/user-record")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserService userService;

    public UserServlet() {
        this.userService = new UserService();
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user-record", userService.getAll().iterator().next());
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-record.jsp");
        dispatcher.forward(request, response);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
