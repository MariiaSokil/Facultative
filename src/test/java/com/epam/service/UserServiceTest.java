package com.epam.service;

import com.epam.dao.UserDao;
import com.epam.model.Role;
import com.epam.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService service;

    @Test
    public void saveNew() {
        User user = new User();
        service.saveNew(user);
        verify(userDao).saveNew(user);
    }
    @Test
    public void getByLogin(){
        service.getByLogin("mariia@gmail.com");
        verify(userDao).findUserByLogin("mariia@gmail.com");
    }

    @Test
    public void isValid(){
        User user = new User();
        user.setPassword("password");
        assertTrue(service.isValid(user,"password"));
        assertFalse(service.isValid(user,"password1"));
    }

    @Test
    public void findAllUsersByRole(){
        User user = new User();
        user.setRole(Role.STUDENT);
        List<User> userList = Collections.singletonList(user);
        when(userDao.findAllUsersByRole(Role.STUDENT)).thenReturn(userList);
        assertNotNull(service.findUserByRole(Role.STUDENT));
        assertTrue(service.findUserByRole(Role.TEACHER).isEmpty());
    }

}