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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        System.out.println("Course id in delete :" + courseId);
        String page;
        if (user.getRole() == Role.ADMIN) {
            courseService.deleteCourse(courseId);
            request.setAttribute("courses", courseService.findAll(true));
            page = "/admin.jsp";
        } else {
            page = "/login.jsp";
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
        rd.forward(request, response);


        /*public void doPost (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {*/
//        HttpSession session = request.getSession();
//        Course course = (Course) session.getAttribute("course");
//        String courseId = request.getParameter("id");
//        String title = request.getParameter("title");
//        String categoryId = request.getParameter("category_id");
//        String categoryName = request.getParameter("category_name");
//        String duration = request.getParameter("duration");
//        String enrollment = request.getParameter("enrollment");
//        String status = request.getParameter("status");
//        String price = request.getParameter("price");
//        String teacherId = request.getParameter("teacher_id");
//        String startDate = request.getParameter("start_date");
//        String students = request.getParameter("students");
//        String removeFlag = request.getParameter("remove course");
//        System.out.println("removeFlag =" + removeFlag);
//        System.out.println("CourseId =" + courseId);
//        System.out.println("CourseTitle =" + title);
//        System.out.println("categoryId =" + categoryId);
//        System.out.println("categoryName =" + categoryName);
//        System.out.println("duration =" + duration);
//        System.out.println("enrollment =" + enrollment);
//        System.out.println("status =" + status);
//        System.out.println("price =" + price);
//        System.out.println("teacherId =" + teacherId);
//        System.out.println("startDate =" + startDate);
//        System.out.println("students size  =" + students);
//
//        if(courseId!=null)
        /*if (course!= null) {
             Course course = new Course();
            course.setId(new Long(courseId));
            course.setTitle(title);
            course.setCategory(new Category(new Long(categoryId), categoryName));
            course.setDuration(new Integer(duration));
            course.setPrice(new Integer(price));
            course.setStatus(Status.valueOf(status));
            course.setTeacher(new User(new Long(teacherId)));
            course.setStartDate(toLocalDate(startDate));
            Set<User> users = new HashSet<>();
            users.add(user);
            course.setStudents(users);
            if (removeFlag !=null && "true".equals(removeFlag)) {
                System.out.println("in if");
                course.setEnrollment(new Integer(enrollment) - 1);
                courseService.updateCourse(course, false);
            } else {
                System.out.println("in else");
                course.setEnrollment(new Integer(enrollment) + 1);
                courseService.updateCourse(course, true);
            }

            //redirect to student page
            request.setAttribute("user", user);
            request.setAttribute("courses", courseService.findAllByStudentId(user.getId()));
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/student.jsp");
            rd.forward(request, response);
        } else {
            //TODO refactor below
            System.out.println("User is not logged in");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("message", "Either user name or password is wrong");
            rd.include(request, response);
        }*/
        }

        private LocalDate toLocalDate (String stringDate){
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
