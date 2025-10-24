package com.newspaper.newspaper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateContentDTO {
    @NotBlank(message = "Content cannot be empty")
    @Size(min = 50, max = 2000, message = "Content must be between 50 and 2000 characters.")
    private String content;
}
