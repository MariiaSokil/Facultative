package com.epam.controllers;

import com.epam.model.Role;
import com.epam.model.User;
import com.epam.service.CourseService;
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

    private final CourseService courseService;
    private final UserService userService;

    public UserServlet() {
        courseService = new CourseService();
        userService = new UserService();
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(password);


        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setLogin(email);
        newUser.setPassword(password);
        newUser.setRole(Role.STUDENT);
        newUser.setStudent(true);
        newUser.setBlocked(false);
        if(userService.getByLogin(email)==null){

        userService.saveNew(newUser);

        //redirect to index page
        request.setAttribute("user", newUser);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    } else {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
        request.setAttribute("message", "A user with this login already exists ");
        rd.include(request, response);
    }

    }
}
