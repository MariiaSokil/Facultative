package com.epam.controllers;

import com.epam.model.Role;
import com.epam.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TeacherServlet.
 * @author M.Sokil
 */

@WebServlet(name = "TeacherServlet", urlPatterns = "/teachers")
public class TeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserService userService;

    public TeacherServlet() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), userService.findUserByRole(Role.TEACHER));
    }
}
