package com.epam.controllers;

import com.epam.model.*;
import com.epam.service.CourseService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "CourseServlet2", urlPatterns = "/courses2")
public class CourseCreateDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CourseService courseService;

    public CourseCreateDeleteServlet() {
        this.courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String courseId = request.getParameter("id");
        String enrollment = request.getParameter("enrollment");
        String page;
        if (user.getRole() == Role.ADMIN) {
            if (enrollment!=null && enrollment.equals("0")) {
                courseService.deleteCourse(new Long(courseId),false);
            } else {
                courseService.deleteCourse(new Long(courseId), true);
            }
            request.setAttribute("courses", courseService.findAll(true));
            page = "/admin.jsp";
        } else {
            page = "/login.jsp";
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
        rd.forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String courseId = request.getParameter("id");
        String title = request.getParameter("title");
        String categoryId = request.getParameter("category_id");
        String duration = request.getParameter("duration");
        String enrollment = request.getParameter("enrollment");
        String status = request.getParameter("status");
        String price = request.getParameter("price");
        String teacherId = request.getParameter("teacher_id");
        String startDate = request.getParameter("start_date");

        System.out.println("CourseId =" + courseId);
        System.out.println("CourseTitle =" + title);
        System.out.println("categoryId =" + categoryId);
        System.out.println("duration =" + duration);
        System.out.println("enrollment =" + enrollment);
        System.out.println("status =" + status);
        System.out.println("price =" + price);
        System.out.println("teacherId =" + teacherId);
        System.out.println("startDate =" + startDate);

        String page;
        if (user.getRole() == Role.ADMIN) {
            Course course = new Course();
            if (courseId!=null && !courseId.trim().isEmpty()) {
                course.setId(new Long(courseId));
            }
            course.setTitle(title);
            course.setCategory(new Category(new Long(categoryId), "dummy"));
            course.setDuration(new Integer(duration));
            course.setPrice(new Integer(price));
            if (enrollment!=null) {
                course.setEnrollment(new Integer(enrollment));
            } else {
                course.setEnrollment(0);
            }
            if (status!=null) {
                course.setStatus(Status.valueOf(status));
            } else {
                course.setStatus(Status.COMING_SOON);
            }
            if (teacherId!=null) {
                course.setTeacher(new User(new Long(teacherId)));
            }
            course.setStartDate(toLocalDate(startDate));
            Set<User> users = new HashSet<>();
            course.setStudents(users);

            if (course.getId()!=null) {
                courseService.updateCourse(course);
            } else {
                courseService.saveNew(course);
            }


            request.setAttribute("courses", courseService.findAll(true));
            page = "/admin.jsp";
        } else {
            page = "/login.jsp";
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
        rd.forward(request, response);
    }

    private LocalDate toLocalDate(String stringDate) {
        DateTimeFormatter formatter;
        if (stringDate.contains(".")) {
            //15.05.2021
            formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        } else {
            //suppose 2021-05-15
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
        return LocalDate.parse(stringDate, formatter);
    }
}
