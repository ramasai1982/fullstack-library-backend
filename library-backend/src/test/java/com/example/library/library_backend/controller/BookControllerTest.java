package com.example.library.library_backend.controller;


import com.example.library.library_backend.repo.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // Full Spring Boot test
@AutoConfigureMockMvc // Enables MockMvc for API testing
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simulates API calls

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testGetBooks() throws Exception {
        // Test GET /books endpoint
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk()); // Expect 200 OK response
    }

    @Test
    void testGetBookById_NotFound() throws Exception {
        // Test GET /books/{id} for a non-existent book
        mockMvc.perform(get("/books/999"))
                .andExpect(status().isNotFound()); // Expect 404 Not Found response
    }
}

