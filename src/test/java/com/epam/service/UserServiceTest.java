package com.epam.service;

import com.epam.exception.UserNotFoundException;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService service;

    @Test
    public void findAll() {
        service.findAll();
        verify(userRepository).findAll();
    }

    @Test
    public void findById() {
        when(userRepository.findById(2L)).thenReturn(Optional.of(new User()));
        service.findById(2L);
        verify(userRepository).findById(2L);
    }

    @Test(expected = UserNotFoundException.class)
    public void findByIdCaseNotFound() {
        service.findById(2L);
        verify(userRepository).findById(2L);
    }

    @Test
    public void save() {
        User user = new User();
        service.save(user);
        verify(userRepository).save(user);
    }

    @Test
    public void deleteById() {
        service.deleteById(3L);
        verify(userRepository).deleteById(3L);
    }

    @Test
    public void updateUser() {
        User userDB = new User().setFirstName("Petrov");
        Long id = new Long(1L);
        when(userRepository.findById(id)).thenReturn(Optional.of(userDB));
        when(userRepository.save(any(User.class))).thenReturn(new User());

        User user = new User();
        user.setFirstName("Ivanov");
        service.updateUser(id, user);

        verify(userRepository).findById(id);
        verify(userRepository).save(userDB.setFirstName(user.getFirstName()));
    }

    @Test(expected = RuntimeException.class)
    public void updateUserCaseNotFound() {
        User userDB = new User().setFirstName("Petrov");
        Long id = new Long(1L);

        User user = new User();
        user.setFirstName("Ivanov");
        service.updateUser(id, user);
    }

    @Test
    public void findAllPage() {
        Pageable pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("firstName")));
        service.findAll(pageRequest);
        verify(userRepository).findAll(pageRequest);
    }
}