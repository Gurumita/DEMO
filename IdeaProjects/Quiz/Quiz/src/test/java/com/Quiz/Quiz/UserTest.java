package com.Quiz.Quiz;

import com.Quiz.Quiz.Models.User;
import com.Quiz.Quiz.Repository.UserRepository;
import com.Quiz.Quiz.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser(){
        User user = new User();
        user.setUsername("Gurumita");
        user.setPassword("Guru@123");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createduser = userService.saveUser(user);

        assertNotNull(createduser);
        assertEquals("Gurumita",user.getUsername());

        verify(userRepository,times(1)).save(user);
    }

    @Test
    public void testFindUserByName(){

        User user = new User(1,"Gurumita","Guru@123");

        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        Optional<User> expected_user = userService.findUserByUsername(user.getUsername());
        assertNotNull(expected_user);
        assertEquals("Gurumita",user.getUsername());

        verify(userRepository,times(1)).findByUsername(user.getUsername());

    }





}