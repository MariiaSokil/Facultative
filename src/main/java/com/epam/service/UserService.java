package com.epam.service;

import com.epam.model.Role;
import com.epam.model.User;

import java.util.*;

public class UserService {
private static Map<String,User> map = new HashMap<>();
static {
    map.put("john@gmail.com", new User(1L, "John", "Doe", Role.STUDENT,"john@gmail.com","1"));
    map.put("mariia@gmail.com", new User(1L, "Mariia", "Sokil", Role.STUDENT,"mariia@gmail.com","1"));
    map.put("ivan@gmail.com", new User(1L, "Ivan", "Ivanov", Role.TEACHER,"ivan@gmail.com","1"));
    map.put("elena@gmail.com", new User(1L, "Elena", "Voron", Role.ADMIN,"elena@gmail.com","1"));
    map.put("igor@gmail.com", new User(1L, "Igor", "Petrov", Role.STUDENT,"igor@gmail.com","1"));
    map.put("vita@gmail.com", new User(1L, "Vita", "Sidorova", Role.STUDENT,"vita@gmail.com","1"));
}


    public Set<User> getAll() {
        return new HashSet<>(map.values());
    }

    public User getStudent() {
        return new User(1L, "John", "Doe", Role.STUDENT);
    }

    public boolean isValid(User user, String password) {
        return user != null && user.getPassword().equals(password);
    }

    public User getByLogin(String login) {
        return map.get(login);
    }
}



