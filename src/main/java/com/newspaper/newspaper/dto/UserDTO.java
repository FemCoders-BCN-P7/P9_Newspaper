package com.newspaper.newspaper.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Incorrect email")
    @NotBlank(message = "Email is mandatoory")
    private String email;
}