
package com.newspaper.newspaper.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.newspaper.newspaper.dto.UserDTO;
import com.newspaper.newspaper.entity.User;
import com.newspaper.newspaper.mapper.UserMapper;
import com.newspaper.newspaper.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        boolean exists = userRepository.findByEmail(userDTO.getEmail()).isPresent();
        if (exists) {
            throw new IllegalArgumentException("User with email " + userDTO.getEmail() + " already exists");
        }
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete. User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
}
