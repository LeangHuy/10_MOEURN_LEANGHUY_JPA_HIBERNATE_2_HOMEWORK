package com.config.practiceentitymanager.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookRequest {
    @NotNull
    @NotBlank
    private String title;
    private String description;
    @NotNull
    @NotBlank
    private String author;
    private LocalDate publicationYear;
}
