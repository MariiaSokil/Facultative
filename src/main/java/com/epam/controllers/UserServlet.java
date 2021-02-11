package com.epam.controllers;

import com.epam.model.Role;
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

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserService userService;

    public UserServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        request.setAttribute("courses", user.getCourses());

        String page;
        if (user.getRole() == Role.TEACHER) {
            page = "/teacher.jsp";
        } else if (user.getRole() == Role.ADMIN) {
            page = "/admin.jsp";
        } else {
            page = "/student.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
        rd.forward(request, response);
    }
}
