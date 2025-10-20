package com.newspaper.newspaper.service;

import org.springframework.http.ResponseEntity;

import com.newspaper.newspaper.model.User;

public interface UserService {

    ResponseEntity<User> addUser (User user);
    
}
