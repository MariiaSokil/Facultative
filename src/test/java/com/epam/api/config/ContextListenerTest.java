package com.epam.api.config;


import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletContextEvent;


@RunWith(MockitoJUnitRunner.class)
public class ContextListenerTest {

    @Mock
    private ServletContextEvent event;
    /*@InjectMocks
    private ContextListener listener;*/

    /*@Test
    public void name() {
        listener.contextDestroyed(event);
        verify(event, never()).getServletContext();
    }*/
}