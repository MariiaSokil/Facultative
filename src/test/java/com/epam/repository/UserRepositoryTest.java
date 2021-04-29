package com.epam.repository;

import com.epam.FacultativeApplication;
import com.epam.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
      //  assertEquals(14,users.size());
       assertTrue(users.isEmpty());
    }
}