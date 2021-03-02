package com.epam.service;

import com.epam.dao.CategoryDao;
import com.epam.dao.UserDao;
import com.epam.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
        Mockito.verify(userDao).saveNew(user);
    }
    @Test
    public void getByLogin(){

        service.getByLogin("mariia@gmail.com");

    }

}