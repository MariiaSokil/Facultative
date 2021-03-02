package com.epam.controllers;

import com.epam.model.User;
import com.epam.service.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseCreateDeleteServletTest{
    @Mock
    private CourseService courseService;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private User user;
    @Mock
    private ServletContext context;
    @Mock
    private RequestDispatcher dispatcher;
    @InjectMocks @Spy
    private CourseCreateDeleteServlet servlet;

    @Test
    public void test_doGet() throws Exception {
        when(servlet.getServletConfig()).thenReturn(servletConfig);
        when(request.getSession()).thenReturn(session);
        when(servletConfig.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);
        when(session.getAttribute("user")).thenReturn(user);
        servlet.doGet(request, response);
        verify(dispatcher).forward(any(), any());
    }

    @Test
    public void test_doPost() throws Exception {
        when(servlet.getServletConfig()).thenReturn(servletConfig);
        when(request.getSession()).thenReturn(session);
        when(servletConfig.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);
        when(session.getAttribute("user")).thenReturn(user);
        servlet.doPost(request, response);
        verify(dispatcher).forward(any(), any());
    }
}