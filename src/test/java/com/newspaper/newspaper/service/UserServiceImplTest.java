package com.newspaper.newspaper.service;

import com.newspaper.newspaper.dto.UserDTO;
import com.newspaper.newspaper.entity.User;
import com.newspaper.newspaper.mapper.UserMapper;
import com.newspaper.newspaper.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_should_Return_Created_User() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Erika");
        userDTO.setEmail("erika@email.com");

        User userEntity = new User();
        userEntity.setName("Erika");
        userEntity.setEmail("erika@email.com");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Erika");
        savedUser.setEmail("erika@email.com");

        UserDTO savedDTO = new UserDTO();
        savedDTO.setId(1L);
        savedDTO.setName("Erika");
        savedDTO.setEmail("erika@email.com");

        when(userRepository.findByEmail("erika@email.com")).thenReturn(Optional.empty());
        when(userMapper.toEntity(userDTO)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(savedUser);
        when(userMapper.toDTO(savedUser)).thenReturn(savedDTO);

        UserDTO result = userService.createUser(userDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Erika", result.getName());
        assertEquals("erika@email.com", result.getEmail());
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void createUser_should_Throw_Exception_when_Email_Exists() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("existing@email.com");

        when(userRepository.findByEmail("existing@email.com")).thenReturn(Optional.of(new User()));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.createUser(userDTO));
        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    void getAllUsers_should_Return_UserDTO_List() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("User1");
        user1.setEmail("user1@email.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("User2");
        user2.setEmail("user2@email.com");

        UserDTO dto1 = new UserDTO();
        dto1.setId(1L);
        dto1.setName("User1");
        dto1.setEmail("user1@email.com");

        UserDTO dto2 = new UserDTO();
        dto2.setId(2L);
        dto2.setName("User2");
        dto2.setEmail("user2@email.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(userMapper.toDTO(user1)).thenReturn(dto1);
        when(userMapper.toDTO(user2)).thenReturn(dto2);

        List<UserDTO> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("User1", result.get(0).getName());
        assertEquals("User2", result.get(1).getName());
    }

    @Test
    void getUserById_should_Return_UserDTO() {
        User user = new User();
        user.setId(1L);
        user.setName("Angela");
        user.setEmail("angela@email.com");

        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setName("Angela");
        dto.setEmail("angela@email.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(dto);

        UserDTO result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("Angela", result.getName());
    }

    @Test
    void getUserById_should_Throw_Exception_when_Not_Found() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.getUserById(1L));
        assertTrue(exception.getMessage().contains("User not found"));
    }

    @Test
    void deleteUser_should_Call_Repository_Delete() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_should_Throw_Exception_when_Not_Found() {
        when(userRepository.existsById(1L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.deleteUser(1L));
        assertTrue(exception.getMessage().contains("Cannot delete"));
    }
}
