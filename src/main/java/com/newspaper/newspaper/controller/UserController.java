package com.newspaper.newspaper.controller;

import org.springframework.web.bind.annotation.RestController;

import com.newspaper.newspaper.model.User;
import com.newspaper.newspaper.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/users")

public class UserController {
    

    private UserService userService; 

    public UserController(UserService userService) {
        this.userService = userService; 
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    
}
