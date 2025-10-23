package com.newspaper.newspaper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDTO {

    private Long id;

    @NotBlank(message = "The title cannot be empty")
    @Size(max =255, message = "The title cannot exceed 255 characters")
    private String title;

    
    @NotBlank(message = "Content cannot be empty")
    @Size(min = 50, max =2000, message = "Content must be between 50 and 2000 characters.")
    private String content;

    private LocalDate publishedAt;

    @NotBlank(message = "The category cannot be empty")
    private String category;

    private UserDTO user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
