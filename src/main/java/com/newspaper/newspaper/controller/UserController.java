package com.newspaper.newspaper.controller;

import com.newspaper.newspaper.dto.UserDTO;
import com.newspaper.newspaper.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/users")

public class UserController {    

    private UserService userService; 

    public UserController(UserService userService) {
        this.userService = userService; 
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
       UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }
}
