package org.study.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.study.validator.BasicInfo;

import java.util.List;

/**
 * User Service manages all staff related to any users.
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
                .orElseThrow(RuntimeException::new);
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

    public Page<User> findAll(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }
}



