package com.epam.service;

import com.epam.dao.CategoryDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryDao dao;

    @InjectMocks
    private CategoryService service;

    @Test
    public void findAll() {
        service.findAll();
        Mockito.verify(dao).findAll();
    }
}