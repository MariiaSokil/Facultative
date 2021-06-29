package org.study.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserAssembler userAssembler;


    @Test
    public void findAll() {

        List<User> userList= Arrays.asList(new User().setId(1L), new User().setId(2L));
        Collection<UserDTO> collectionDTO =new ArrayList<>();

        when(userService.findAll(Specification.where(null))).thenReturn(userList);
        when(userMapper.toDTO(userList)).thenReturn(collectionDTO);

        userController.findAll(1L);
    }

    @Test
    public void findById() {
        User user= new User();
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);
        UserController.UserType userType=new UserController.UserType(userDTO);

        when(userService.findById(1L)).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(userDTO);
        when(userAssembler.toModel(userDTO)).thenReturn(userType);
        userController.findById(1L);
        verify(userService).findById(1L);
    }

    @Test
    public void createNew() {
        UserDTO newUserDTO= new UserDTO();
        newUserDTO.setId(1L);

        User user = new User();
        when(userMapper.toMODEL(newUserDTO)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(20L);
        UserController.UserType userType = new UserController.UserType(userDTO);
        when(userMapper.toDTO(user)).thenReturn(userDTO);
        when(userAssembler.toModel(userDTO)).thenReturn(userType);

        userController.createNew(newUserDTO);
        verify(userService).save(user);
    }

    @Test
    public void deleteById() {
        userController.deleteById(1L);
        verify(userService).deleteById(1L);

    }

    @Test
    public void updateUser() {
        UserDTO userDTO= new UserDTO();
        userDTO.setId(1L);

        User u = new User();
        when(userMapper.toMODEL(userDTO)).thenReturn(u);

        User user=new User();
        user.setFirstName("Romanov");
        when(userService.updateUser(1L, u)).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserController.UserType userType = new UserController.UserType(userDTO);
        when(userAssembler.toModel(userDTO)).thenReturn(userType);

        userController.updateUser(1L,userDTO);
        verify(userService).updateUser(1L,u);
    }
}