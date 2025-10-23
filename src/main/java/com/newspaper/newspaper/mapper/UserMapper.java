package com.newspaper.newspaper.mapper;

import org.springframework.stereotype.Component;

import com.newspaper.newspaper.dto.UserDTO;
import com.newspaper.newspaper.entity.User;

@Component
public class UserMapper {

    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        return user;
    }

    public UserDTO toDTO(User savedUser) {
        if (savedUser == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(savedUser.getId());
        dto.setName(savedUser.getName());
        dto.setEmail(savedUser.getEmail());

        return dto;
    }

}
