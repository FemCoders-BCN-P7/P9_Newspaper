package com.newspaper.newspaper.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.newspaper.newspaper.model.User;
import com.newspaper.newspaper.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> addUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUser'");
    }


}
