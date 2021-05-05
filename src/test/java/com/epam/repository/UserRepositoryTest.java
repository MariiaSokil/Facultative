package com.epam.repository;

import com.epam.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
//@SpringBootTest(classes = FacultativeApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll() {
        List<User> users = userRepository.findAll();
        assertEquals(0, users.size());
    }

    @Test
    public void findById() {
        User user = new User();
        user.setId(1L);

        userRepository.save(user);
        assertTrue(userRepository.findById(1L).isPresent());
    }

    @Test
    public void createNew() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Ivanov");
        userRepository.save(user);
        assertNotNull(userRepository.findById(1L));
    }

    @Test
    public void deleteById() {
        User user = new User();
        user.setId(1L);

        userRepository.save(user);
        assertNotNull(userRepository.findById(1L));
        userRepository.deleteById(1L);
        assertFalse(userRepository.findById(1L).isPresent());
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Ivanov");

        userRepository.save(user);
        assertTrue(userRepository.findById(1L).isPresent());
        user.setFirstName("Petrov");
        userRepository.save(user);

    }
}