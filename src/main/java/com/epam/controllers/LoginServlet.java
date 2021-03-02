package com.epam.controllers;

import com.epam.model.Course;
import com.epam.model.Role;
import com.epam.model.User;
import com.epam.service.CourseService;
import com.epam.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * LoginServlet.
 * @author M.Sokil
 */

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserService userService;
    private final CourseService courseService;

    public LoginServlet() {
        this.userService = new UserService();
        courseService = new CourseService();
    }

    /**
     * Login user and redirect to user page depend on the role.
     * Create a session for current user.
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getByLogin(request.getParameter("uname"));
        if (userService.isValid(user, request.getParameter("psw"))) {
            System.out.println("User authenticated!");

            //session management
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30*60);

            Cookie userName = new Cookie("user", user.getLogin());
            userName.setMaxAge(30*60);
            response.addCookie(userName);
            //session management end

            request.setAttribute("user", user);
            String page;
            if (user.getRole() == Role.TEACHER) {
                page = "/teacher.jsp";
            } else if (user.getRole() == Role.ADMIN) {
                List<Course> courses = courseService.findAll(true);
                request.setAttribute("courses", courses);
                page = "/admin.jsp";
            } else {
                List<Course> courses = courseService.findAllByStudentId(user.getId());
                if (courses.isEmpty()) {
                    page = "/index.jsp";
                } else {
                    request.setAttribute("courses", courses);
                    page = "/student.jsp";
                }
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("message", "Either user name or password is wrong");
            rd.include(request, response);
        }

    }
}