package com.example.library.library_backend.service;

import com.example.library.library_backend.model.Book;
import com.example.library.library_backend.repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Enables Mockito for testing
class BookServiceTest {

    @Mock
    private BookRepository bookRepository; // Mock BookRepository to avoid real DB calls

    @InjectMocks
    private BookService bookService; // Injects the mocked repository into BookService

    private Book sampleBook; // Sample book object for testing

    @BeforeEach
    void setUp() {
        // Initialize a sample book that will be used in multiple tests
        sampleBook = new Book(1L, "Spring Boot in Action", "Craig Walls", "1234567890", 2016, "A great book on Spring Boot.");
    }

    /**
     * Test retrieving a book by ID when it exists.
     */
    @Test
    void testGetBookById_Success() {
        // Simulate repository returning the book when searched by ID
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        // Retrieve book using service
        Optional<Book> book = bookService.getBookById(1L);

        // Assert that the book is present
        assertTrue(book.isPresent());
        assertEquals("Spring Boot in Action", book.get().getTitle()); // Ensure correct title
    }

    /**
     * Test retrieving a book by ID when it does NOT exist.
     * Expecting NoSuchElementException to be thrown.
     */
    @Test
    void testGetBookById_NotFound() {
        // Simulate repository returning empty Optional for non-existent book
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        // Assert that NoSuchElementException is thrown when book is not found
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
                bookService.getBookById(99L));

        // Verify the exception message is correct
        assertEquals("Book not found with ID: 99", exception.getMessage());
    }

    /**
     * Test retrieving all books from the repository.
     */
    @Test
    void testGetAllBooks() {
        // Simulate repository returning a list with one book
        when(bookRepository.findAll()).thenReturn(List.of(sampleBook));

        // Retrieve all books
        List<Book> books = bookService.getAllBooks();

        // Assert that the book list is not empty and contains the correct number of books
        assertEquals(1, books.size());
        assertEquals("Spring Boot in Action", books.get(0).getTitle()); // Ensure correct book is retrieved
    }
}

