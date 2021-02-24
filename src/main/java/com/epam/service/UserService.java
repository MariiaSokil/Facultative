package com.epam.service;

import com.epam.dao.UserDao;
import com.epam.model.Role;
import com.epam.model.User;

import java.util.List;

public class UserService {

    private final UserDao userDao;
    public UserService() {
        userDao = new UserDao();
    }

    public boolean isValid(User user, String password) {
        return user != null && user.getPassword().equals(password);
    }

    public User getByLogin(String login) {
        return userDao.findUserByLogin(login);
    }

    public List<User> findUserByRole(Role role) {
        return userDao.findAllUsersByRole(role);
    }

    public void saveNew(User user) {
        userDao.saveNew(user);
    }
}



