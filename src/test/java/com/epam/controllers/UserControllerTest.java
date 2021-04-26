package com.epam.controllers;

import com.epam.controllers.assembler.UserAssembler;
import com.epam.controllers.type.UserType;
import com.epam.dto.UserDTO;
import com.epam.exception.UserNotFoundException;
import com.epam.mappers.impl.UserMapper;
import com.epam.model.Role;
import com.epam.model.User;
import com.epam.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;


@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserAssembler userAssembler;

    @Test
    public void findAll() throws Exception {
        User user1 = new User().setId(1L)
                .setFirstName("Males")
                .setLastName("Morales")
                .setLogin("males@gmail.com")
                .setPassword("1111")
                .setBlocked(false)
                .setStudent(true)
                .setRole(Role.STUDENT);
        User user2 = new User().setId(2L)
                .setFirstName("Super")
                .setLastName("Man")
                .setLogin("super@gmail.com")
                .setPassword("2222")
                .setBlocked(false)
                .setStudent(true)
                .setRole(Role.STUDENT);

        when(userService.findAll()).thenReturn(Arrays.asList(user1,user2));

        mockMvc.perform(MockMvcRequestBuilders.get("/users"));
    }

    @Test
    public void findById() throws Exception {
        User user= new User();
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);
        UserType userType=new UserType(userDTO);

        when(userService.findById(1L)).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(userDTO);
        when(userAssembler.toModel(userDTO)).thenReturn(userType);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(HAL_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));

    }

    @Test
    public void findByIdCaseNotFound() throws Exception {
        when(userService.findById(1L)).thenThrow(UserNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorType").value("DATABASE_ERROR_TYPE"));

    }
    @Test
    public void createNew() throws Exception {
/*
        UserDTO userDTO=new UserDTO();
        userDTO.setFirstName("Ivanov");
        userDTO.setFirstName("Ivan");
        userDTO.setLogin("ivanov@gmail.com");
        userDTO.setPassword("11111");
        userDTO.setRole(String.valueOf(Role.STUDENT));
        userDTO.setBlocked(false);
        userDTO.setStudent(true);

        User user= new User();
        user.getId();

        UserType userType=new UserType(userDTO);

        when(userMapper.toMODEL(userDTO)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(userDTO);
        when(userAssembler.toModel(userDTO)).thenReturn(userType);

        mockMvc.perform(MockMvcRequestBuilders.post("/users"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect((ResultMatcher) MediaType.APPLICATION_JSON)
        .andExpect(js);*/

    }
}
