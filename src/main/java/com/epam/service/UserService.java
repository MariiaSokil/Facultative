package com.epam.service;

import com.epam.dao.UserDao;
import com.epam.exception.UserNotFoundException;
import com.epam.model.Role;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import com.epam.validator.BasicInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * //TODO.
 */
@Log4j2
@Service @RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(@Validated(BasicInfo.class) User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new); //new UserNotFoundException(id))
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, @Validated(BasicInfo.class) User user) {
        return userRepository.findById(id)
                .map(userFromDB -> {
                    userFromDB.setFirstName(user.getFirstName());
                    userFromDB.setLastName(user.getLastName());
                    userFromDB.setRole(user.getRole());
                    userFromDB.setLogin(user.getLogin());
                    return userRepository.save(userFromDB);
                })
                .orElseThrow(() -> new RuntimeException("User with id=" + id + " not found"));
    }

    /**
     * Returns true, if password matches with User password, else - false.
     *
     * @param user     User.
     * @param password String.
     * @return true, if User is valid.
     */
    public boolean isValid(@Validated(BasicInfo.class) User user, String password) {
        return user != null /*&& user.getPassword().equals(password)*/;
    }

    /**
     * Returns a user by login
     *
     * @param login User login(e-mail).
     * @return User entity.
     */
    public User getByLogin(String login) {
        //return userDao.findUserByLogin(login);
        return null;
    }

    /**
     * Returns list of user by role.
     *
     * @param role User role.
     * @return list of User by role.
     */
    public List<User> findUserByRole(Role role) {
        //return userDao.findAllUsersByRole(role);
        return null;
    }


}



