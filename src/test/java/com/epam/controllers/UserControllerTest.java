package com.epam.controllers;

import com.epam.controllers.assembler.UserAssembler;
import com.epam.controllers.type.UserType;
import com.epam.dto.UserDTO;
import com.epam.exception.UserNotFoundException;
import com.epam.mappers.impl.UserMapper;
import com.epam.model.Role;
import com.epam.model.User;
import com.epam.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
        /*List<User> userList= new ArrayList<>();
        Collection<UserDTO> collectionDTO =new ArrayList<>();
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
        userList.add(user1);
        userList.add(user2);

        when(userService.findAll()).thenReturn(userList);
        when(userMapper.toDTO(userList)).thenReturn(collectionDTO);


        mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users").exists())
                .andExpect(jsonPath("$.users[*].id").isNotEmpty());
*/
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

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON))
                .andExpect(jsonPath("$.id").value("1"));

    }

    @Test
    public void findByIdCaseNotFound() throws Exception {
        when(userService.findById(1L)).thenThrow(UserNotFoundException.class);
        mockMvc.perform(get("/users/1"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorType").value("DATABASE_ERROR_TYPE"));

    }


    /*@ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public UserType createNew(@RequestBody UserDTO newUserDto) {
        log.info("Got request for user creation:{}", newUserDto);
        User user = userMapper.toMODEL(newUserDto);
        user=userService.save(user);
        UserDTO userDTO=userMapper.toDTO(user);
        return userAssembler.toModel(userDTO);
    }*/
    @Test
    public void createNew() throws Exception {

        UserDTO newUserDTO= new UserDTO();
        newUserDTO.setFirstName("Ivanov");
        newUserDTO.setLastName("Ivan");
        newUserDTO.setRole(String.valueOf(Role.STUDENT));
        newUserDTO.setLogin("ivanov@gmail.com");
        newUserDTO.setPassword("11111");
        newUserDTO.setBlocked(false);
        newUserDTO.setStudent(true);


        User user = new User();
        when(userMapper.toMODEL(newUserDTO)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);
        UserDTO userDTO= new UserDTO();
        userDTO.setId(20L);
        UserType userType = new UserType(userDTO);
        when(userMapper.toDTO(user)).thenReturn(userDTO);
        when(userAssembler.toModel(userDTO)).thenReturn(userType);

        mockMvc.perform(post("/users")
                .content(asJsonString(newUserDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
