package com.epam.controllers;

import com.epam.service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentServlet", urlPatterns = "/student-record")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentService studentService = null;

    public void init() {
        studentService = new StudentService();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("student-record", studentService.getStudent());
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-record.jsp");
        dispatcher.forward(request, response);

        request.setAttribute("student-record", studentService.getStudents());
        dispatcher = request.getRequestDispatcher("student-record.jsp");
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
