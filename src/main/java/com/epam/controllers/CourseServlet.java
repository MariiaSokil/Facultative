package com.epam.controllers;

import com.epam.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}
