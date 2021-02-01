package com.epam.service;

import com.epam.model.Role;
import com.epam.model.User;

import java.util.Arrays;
import java.util.List;

public class UserService {
    /*StudentService instance;

    public StudentService() {
    }

    public StudentService getInstance() {
        if(instance==null)
            instance = new StudentService();
        return instance;
    }*/

    public List<User> getAll() {
        return Arrays.asList(
                new User(1L, "John", "Doe", Role.STUDENT),
                new User(2L, "Jane", "Goodall", Role.ADMIN),
                new User( 3L, "Max", "Born", Role.TEACHER)
        );
    }

    public User getStudent() {
        return new User(1L, "John", "Doe", Role.STUDENT);
    }

}



