package com.epam.controllers;

import com.epam.model.Role;
import com.epam.model.User;
import com.epam.service.CourseService;

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

    private final CourseService courseService;

    public UserServlet() {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);

        String page;
        if (user.getRole() == Role.TEACHER) {
            page = "/teacher.jsp";
        } else if (user.getRole() == Role.ADMIN) {
            request.setAttribute("courses", courseService.findAll(true));
            page = "/admin.jsp";
        } else {
            request.setAttribute("courses", courseService.findAllByStudentId(user.getId()));
            page = "/student.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
        rd.forward(request, response);
    }
}
