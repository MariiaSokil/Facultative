package com.epam.controllers;

import com.epam.model.Role;
import com.epam.model.User;
import com.epam.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "CourseServlet", urlPatterns = "/courses")
public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CourseService courseService = null;

    public void init() {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), courseService.getAll());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String categoryId = request.getParameter("category_id");
        String categoryName = request.getParameter("category_name");
        String duration = request.getParameter("duration");
        String enrollment = request.getParameter("enrollment");
        String status = request.getParameter("status");
        String price = request.getParameter("price");
        String teacherId = request.getParameter("teacher_id");
        System.out.println("CourseId =" + id);
        System.out.println("CourseTitle =" + title);
        System.out.println("categoryId =" + categoryId);
        System.out.println("categoryName =" + categoryName);
        System.out.println("duration =" + duration);
        System.out.println("enrollment =" + enrollment);
        System.out.println("status =" + status);
        System.out.println("price =" + price);
        System.out.println("teacherId =" + teacherId);
        if (user!= null) {
            request.setAttribute("user", user);
            request.setAttribute("courses", user.getCourses());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/student.jsp");
            rd.forward(request, response);
        } else {
            System.out.println("User is not logged in");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("message", "Either user name or password is wrong");
            rd.include(request, response);
        }
    }
}
