package com.newspaper.newspaper.controller;

import com.newspaper.newspaper.dto.UserDTO;
import com.newspaper.newspaper.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldCreateUserSuccessfully() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("Mio");
        userDTO.setEmail("mio@email.com");

        Mockito.when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Mio"))
                .andExpect(jsonPath("$.email").value("mio@email.com"));
    }

    @Test
    void shouldReturnAllUsers() throws Exception {
        UserDTO user1 = new UserDTO(1L, "Mio", "mio@email.com");
        UserDTO user2 = new UserDTO(2L, "Angela", "angela@email.com");

        Mockito.when(userService.getAllUsers()).thenReturn(List.of(user1, user2));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Mio"))
                .andExpect(jsonPath("$[1].name").value("Angela"));
    }

    @Test
    void shouldDeleteUserSuccessfully() throws Exception {
        Mockito.doNothing().when(userService).deleteUser(anyLong());

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNoContent());
    }
}
