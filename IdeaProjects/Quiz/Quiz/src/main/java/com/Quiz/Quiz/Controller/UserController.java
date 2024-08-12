package com.Quiz.Quiz.Controller;

import com.Quiz.Quiz.Models.User;
import com.Quiz.Quiz.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

//    @PostMapping("/login")
//    public Optional<User> loginUser(@RequestBody User user) {
//        Optional<User> foundUser = userService.findUserByUsername(user.getUsername());
//        if (foundUser.isPresent() && foundUser.get().getPassword().equals(user.getPassword())) {
//            return foundUser;
//        }
//        return Optional.empty();
//    }

    @PostMapping("/login")
    public Optional<User> loginUser(HttpSession httpSession, @RequestBody User user) {
        Optional<User> foundUser = userService.findUserByUsername(user.getUsername());
        httpSession.setAttribute("userId", user.getUser_id());
        httpSession.setAttribute("username", user.getUsername());
        if (foundUser.isPresent() && foundUser.get().getPassword().equals(user.getPassword())) {
            return foundUser;
        }
        return Optional.empty();
    }

    @GetMapping("/get")
    public String getSession(HttpSession httpSession){
        String username = (String) httpSession.getAttribute("username");
        if(!username.isBlank()){
            return username;
        }
        return "No session values found";
    }
}