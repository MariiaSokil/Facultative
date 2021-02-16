package com.epam.controllers;

import com.epam.model.*;
import com.epam.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        String courseId = request.getParameter("id");
        String title = request.getParameter("title");
        String categoryId = request.getParameter("category_id");
        String categoryName = request.getParameter("category_name");
        String duration = request.getParameter("duration");
        String enrollment = request.getParameter("enrollment");
        String status = request.getParameter("status");
        String price = request.getParameter("price");
        String teacherId = request.getParameter("teacher_id");
        String startDate = request.getParameter("start_date");
        System.out.println("CourseId =" + courseId);
        System.out.println("CourseTitle =" + title);
        System.out.println("categoryId =" + categoryId);
        System.out.println("categoryName =" + categoryName);
        System.out.println("duration =" + duration);
        System.out.println("enrollment =" + enrollment);
        System.out.println("status =" + status);
        System.out.println("price =" + price);
        System.out.println("teacherId =" + teacherId);
        System.out.println("startDate =" + startDate);
        if (user!= null) {
            Course course = new Course();
            course.setId(new Long(courseId));
            course.setTitle(title);
            course.setCategory(new Category(new Long(categoryId), categoryName));
            course.setDuration(new Integer(duration));
            course.setPrice(new Integer(price));
            course.setEnrollment(new Integer(enrollment) + 1);
            course.setStatus(Status.valueOf(status));
            course.setTeacher(new User(new Long(teacherId)));
            course.setStartDate(toLocalDate(startDate));
            courseService.updateCourse(course);

            //redirect to student page
            List<Course> userCourses = courseService.findAllByStudentId(user.getId());
            System.out.println("User courses: " + userCourses.size());
            request.setAttribute("user", user);
            request.setAttribute("courses", userCourses);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/student.jsp");
            rd.forward(request, response);
        } else {
            //TODO refactor below
            System.out.println("User is not logged in");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("message", "Either user name or password is wrong");
            rd.include(request, response);
        }
    }

    private LocalDate toLocalDate(String stringDate) {
        //15.05.2021
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(stringDate, formatter);
    }
}
