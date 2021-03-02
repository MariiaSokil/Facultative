package com.epam.controllers;

import com.epam.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServletTest {

    @Mock
    private CategoryService categoryService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletOutputStream outputStream;
    @InjectMocks
    private CategoryServlet servlet;

    @Test
    public void test() throws Exception {
        when(response.getOutputStream()).thenReturn(outputStream);
        when(categoryService.findAll()).thenReturn(new ArrayList<>());
        servlet.doGet(request, response);
        verify(categoryService).findAll();
    }
}