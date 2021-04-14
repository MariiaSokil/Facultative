package com.epam.controllers;


import com.epam.model.User;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserServlet.
 *
 * @author M.Sokil
 */

@RestController
@RequiredArgsConstructor
public class UserController {
    private static final long serialVersionUID = 1L;

    private final UserService userService;

    /**
     * Directions according to the role.
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        request.setAttribute("user", user);
//
//        String page;
//        if (user.getRole() == Role.TEACHER) {
//            page = "/teacher.jsp";
//        } else if (user.getRole() == Role.ADMIN) {
//            request.setAttribute("courses", courseService.findAll(true));
//            page = "/admin.jsp";
//        } else {
//            request.setAttribute("courses", courseService.findAllByStudentId(user.getId()));
//            page = "/student.jsp";
//        }
//        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
//        rd.forward(request, response);
//    }
//
//    /**
//     * Register a new user.
//     * If the user is new then register him and redirect to home page.
//     * If the user is exist, message "A user with this login already exists "
//     */
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            String firstName = request.getParameter("firstName");
//            String lastName = request.getParameter("lastName");
//            String email = request.getParameter("email");
//            String password = request.getParameter("password");
//
//            User newUser = new User();
//            newUser.setFirstName(firstName);
//            newUser.setLastName(lastName);
//            newUser.setLogin(email);
//            newUser.setPassword(password);
//            newUser.setRole(Role.STUDENT);
//            newUser.setStudent(true);
//            newUser.setBlocked(false);
//            if(userService.getByLogin(email)==null){
//
//            userService.saveNew(newUser);
//
//            //redirect to index page
//            request.setAttribute("user", newUser);
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
//            rd.forward(request, response);
//        } else {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
//            request.setAttribute("message", "A user with this login already exists ");
//            rd.include(request, response);
//        }
//
//    }
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);

    }

    @PostMapping("/users")
    public User createNew(@RequestBody User newUser) {
        return userService.save(newUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, User user) {
        return userService.updateUser(id, user);
    }
}
