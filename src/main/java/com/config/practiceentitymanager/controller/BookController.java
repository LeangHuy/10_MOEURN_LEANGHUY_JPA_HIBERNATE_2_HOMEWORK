package com.config.practiceentitymanager.controller;

import com.config.practiceentitymanager.model.Book;
import com.config.practiceentitymanager.model.dto.BookRequest;
import com.config.practiceentitymanager.repository.BookRepository;
import com.config.practiceentitymanager.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @PostMapping
    @Operation(summary = "Create new book.")
    public ResponseEntity<ApiResponse<Book>> createBook(@Valid @RequestBody BookRequest bookRequest) {
        ApiResponse<Book> response = ApiResponse.<Book>builder()
                .message("Create book successfully.")
                .payload(bookRepository.createBook(bookRequest))
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
