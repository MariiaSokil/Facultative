package com.epam.config.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletContextEvent;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ContextListenerTest {

    @Mock
    private ServletContextEvent event;
    @InjectMocks
    private ContextListener listener;

    @Test
    public void name() {
        listener.contextDestroyed(event);
        verify(event, never()).getServletContext();
    }
}