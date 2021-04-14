package com.epam.service;

import com.epam.dao.UserDao;
import com.epam.model.Role;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Data access object for User model.
 */
@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    public UserService() {
        userDao = new UserDao();
    }
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    /**
     * Returns true, if password matches with User password, else - false.
     * @param user User.
     * @param password  String.
     * @return true, if User is valid.
     */
    public boolean isValid(User user, String password) {
        return user != null /*&& user.getPassword().equals(password)*/;
    }

    /**
     * Returns a user by login
     * @param login User login(e-mail).
     * @return User entity.
     */
    public User getByLogin(String login) {
        return userDao.findUserByLogin(login);
    }

    /**
     * Returns list of user by role.
     * @param role User role.
     * @return list of User by role.
     */
    public List<User> findUserByRole(Role role) {
        return userDao.findAllUsersByRole(role);
    }

    /**
     * Save a new user.
     *@param user User identifier.
     */
    public void saveNew(User user) {
        userDao.saveNew(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}



