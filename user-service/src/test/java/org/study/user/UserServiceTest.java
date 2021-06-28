package org.study.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void findAll() {
        List<User> userList = Arrays.asList(new User().setId(1L), new User().setId(2L));
        Mockito.when(userRepository.findAll(Specification.where(null))).thenReturn(userList);
        userService.findAll(Specification.where(null));
        verify(userRepository).findAll(Specification.where(null));
    }

    @Test
    public void save() {
        User user = new User().setId(1L);
        userService.save(user);
        verify(userRepository).save(user);
    }

   /* @Test
    public void assignCourseToUser() {
        Optional<User> optionalUser = userRepository.findById(1L);
        when(userRepository.findById(1l)).thenReturn(optionalUser);

    } */

    @Test
    public void findById() {
        Optional<User> optionalUser = Optional.of(new User().setId(1L));
        when(userRepository.findById(1L)).thenReturn(optionalUser);
        User result = userService.findById(1l);
        verify(userRepository).findById(1L);

        assertEquals(optionalUser.get(), result);
    }

    @Test(expected = RuntimeException.class)
    public void findByIdCaseNotFound() {
        Optional<User> optionalUser = Optional.empty();
        when(userRepository.findById(1L)).thenReturn(optionalUser);
        userService.findById(1l);
    }


    @Test
    public void findByIdWithCourses() {
        Optional<User> optionalUser = Optional.of(new User().setId(1L));
        when(userRepository.findById(1L)).thenReturn(optionalUser);
        User result = userService.findByIdWithCourses(1l);
        verify(userRepository).findById(1L);

        assertEquals(optionalUser.get(), result);
    }

    @Test(expected = RuntimeException.class)
    public void findByIdWithCoursesCaseNotFound() {
        Optional<User> optionalUser = Optional.empty();
        when(userRepository.findById(1L)).thenReturn(optionalUser);
        userService.findByIdWithCourses(1l);
    }


    @Test
    public void deleteById() {
        userService.deleteById(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    public void updateUser() {
        User user = new User().setId(1L).setFirstName("Barak");
        Optional<User> optionalUser = Optional.of(new User().setId(1L).setFirstName("Ivan"));
        when(userRepository.findById(1L)).thenReturn(optionalUser);
        when(userRepository.save(user)).thenReturn(user);
        userService.updateUser(1L,user);
        verify(userRepository).findById(1L);
        verify(userRepository).save(user);
    }

    @Test(expected = RuntimeException.class)
    public void updateUserCaseNotFound() {
        User user = new User().setId(1L).setFirstName("Barak");
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        userService.updateUser(1L,user);
        verify(userRepository).findById(1L);
        verify(userRepository,never()).save(any());
    }
}