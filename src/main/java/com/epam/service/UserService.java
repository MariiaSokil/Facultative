package com.epam.service;

import com.epam.model.Role;
import com.epam.model.User;

import java.util.*;

public class UserService {
private static Map<String,User> map = new HashMap<>();
    static {
        map.put("john@gmail.com", new User(1L, "John", "Doe", Role.STUDENT,"john@gmail.com","1"));
        map.put("mariia@gmail.com", new User(2L, "Mariia", "Sokil", Role.STUDENT,"mariia@gmail.com","2"));
        map.put("ivan@gmail.com", new User(3L, "Ivan", "Ivanov", Role.TEACHER,"ivan@gmail.com","3"));
        map.put("elena@gmail.com", new User(4L, "Elena", "Voron", Role.ADMIN,"elena@gmail.com","4"));
        map.put("igor@gmail.com", new User(5L, "Igor", "Petrov", Role.STUDENT,"igor@gmail.com","5"));
        map.put("vita@gmail.com", new User(6L, "Vita", "Sidorova", Role.STUDENT,"vita@gmail.com","6"));
    }

    public Set<User> getAll() {
        return new HashSet<>(map.values());
    }

    public boolean isValid(User user, String password) {
        return user != null && user.getPassword().equals(password);
    }

    public User getByLogin(String login) {
        return map.get(login);
    }
}



