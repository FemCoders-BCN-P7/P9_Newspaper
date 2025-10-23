/*package com.newspaper.newspaper.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.newspaper.newspaper.dto.UserDTO;
import com.newspaper.newspaper.mapper.UserMapper;
import com.newspaper.newspaper.model.User;
import com.newspaper.newspaper.repository.UserRepository;

@SpringBootTest
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("Suraya");
        user.setEmail("suraya@email.com");

        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("Suraya");
        userDTO.setEmail("suraya@email.com");
    }

      @Test
    void createUser_Success() {
        when(userRepository.findById(userDTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.createUser(userDTO);

        assertNotNull(result);
        assertEquals(userDTO.getName(), result.getName());
        verify(userRepository, times(1)).save(user);
    }

}
*/