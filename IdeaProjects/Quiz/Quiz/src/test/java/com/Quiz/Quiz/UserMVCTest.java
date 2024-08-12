package com.Quiz.Quiz;

import com.Quiz.Quiz.Controller.UserController;
import com.Quiz.Quiz.Models.User;
import com.Quiz.Quiz.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(UserController.class)
public class UserMVCTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    private ObjectMapper mapper;
    private List<User> users;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mapper = new ObjectMapper();
        users = new ArrayList<>();

        User user1 = new User();
        user1.setUsername("Gurumita");
        user1.setPassword("Guru@123");
        users.add(user1);
    }
    @Test
    public void testRegisterUser() throws Exception {
        String userJson = "{\"user_id\":1,\"username\":\"Gurumita\",\"password\":\"Guru@123\"}";
        String expectedResponseJson = "{\"user_id\":1,\"username\":\"Gurumita\",\"password\":\"Guru@123\"}";
        User user = new User();
        user.setUser_id(1);
        user.setUsername("Gurumita");
        user.setPassword("Guru@123");

        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseJson));;
    }
}