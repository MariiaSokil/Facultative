package com.epam.controllers;


import com.epam.controllers.assembler.UserAssembler;
import com.epam.controllers.type.UserType;
import com.epam.dto.UserDTO;
import com.epam.mappers.impl.UserMapper;
import com.epam.model.User;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    private final UserAssembler userAssembler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public Collection<UserDTO> findAll() {
        return userMapper.toDTO(userService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}")
    public UserDTO findById(@PathVariable Long id) {
        log.info("User found by id: id {}", id);
        return userMapper.toDTO(userService.findById(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public UserType createNew(@RequestBody UserDTO newUserDto) {
        log.info("Got request for user creation:{}", newUserDto);
        User user = userMapper.toMODEL(newUserDto);
        return userAssembler.toModel(userMapper.toDTO(userService.save(user)));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable Long id) {
        log.info("User deleted: id {}", id);
        userService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable Long id, UserDTO userDTO) {
        log.info("User updated:{}", userDTO);
        User u = userMapper.toMODEL(userDTO);
        return userMapper.toDTO(userService.updateUser(id, u));
    }


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
}
