package com.epam.controllers;


import com.epam.controllers.assembler.UserAssembler;
import com.epam.controllers.type.UserType;
import com.epam.dto.CourseDTO;
import com.epam.dto.UserDTO;
import com.epam.mappers.impl.UserMapper;
import com.epam.model.Course;
import com.epam.model.User;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
    public UserType findById(@PathVariable Long id) {
        log.info("User found by id: id {}", id);
        return userAssembler.toModel(userMapper.toDTO(userService.findById(id)));
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
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("User deleted: id {}", id);
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/users/{id}")
    public UserType updateUser(@PathVariable Long id, UserDTO userDTO) {
        log.info("User updated:{}", userDTO);
        User u = userMapper.toMODEL(userDTO);
        return userAssembler.toModel(userMapper.toDTO(userService.updateUser(id, u)));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/page")
    public Page<UserDTO> getPage(int pageNum, int size) {
        Pageable pageRequest = PageRequest.of(pageNum, size, Sort.by(Sort.Order.asc("firstName")));
        Page<User> page = userService.findAll(pageRequest);
        Collection<UserDTO> dtos = userMapper.toDTO(page.getContent());
        List<UserDTO> listDtos = new ArrayList<>(dtos);
        return new PageImpl<>(listDtos, pageRequest, page.getTotalElements());
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
