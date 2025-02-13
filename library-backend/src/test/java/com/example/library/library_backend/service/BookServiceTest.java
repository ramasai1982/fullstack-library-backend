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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book sampleBook;

    @BeforeEach
    void setUp() {
        sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setTitle("Spring Boot in Action");
        sampleBook.setAuthor("Craig Walls");
        sampleBook.setIsbn("987654321");
        sampleBook.setPublicationYear(2025);
        sampleBook.setDescription("A great book about Spring Boot.");
    }

    // 1. Test adding book
    @Test
    void testCreateBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(sampleBook);
        Book savedBook = bookService.createBook(sampleBook);
        assertNotNull(savedBook);
        assertEquals("Spring Boot in Action", savedBook.getTitle());
    }

    // 2. Test if all books are listed
    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(sampleBook));
        List<Book> books = bookService.getAllBooks();
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
    }

    // 3. Test to get book by ID
    @Test
    void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));
        Optional<Book> book = bookService.getBookById(1L);
        assertTrue(book.isPresent());
        assertEquals("Spring Boot in Action", book.get().getTitle());
    }

    // 4. Test to update book by iID
    @Test
    void testUpdateBook() {
        Book updatedBook = new Book(1L, "Updated Title", "New Author", "123456789", 2024, "Updated Description");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        Optional<Book> result = bookService.updateBook(1L, updatedBook);
        assertTrue(result.isPresent());
        assertEquals("Updated Title", result.get().getTitle());
    }

    // 5. Test to delete book by ID
    @Test
    void testDeleteBook() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1L);

        boolean deleted = bookService.deleteBook(1L);
        assertTrue(deleted);
    }

    // 6. Test search book by Title
    @Test
    void testSearchBooksByTitle() {
        when(bookRepository.findByTitleContainingIgnoreCase("Spring"))
                .thenReturn(List.of(sampleBook));

        List<Book> books = bookService.searchBooks("Spring", null);
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
        assertEquals("Spring Boot in Action", books.get(0).getTitle());
    }

    // 7. Test search book by Author
    @Test
    void testSearchBooksByAuthor() {
        when(bookRepository.findByAuthorContainingIgnoreCase("Craig"))
                .thenReturn(List.of(sampleBook));

        List<Book> books = bookService.searchBooks(null, "Craig");
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
        assertEquals("Craig Walls", books.get(0).getAuthor());
    }

    // 8. TestTest search book by Title & Author
    @Test
    void testSearchBooksByTitleAndAuthor() {
        when(bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase("Spring", "Craig"))
                .thenReturn(List.of(sampleBook));

        List<Book> books = bookService.searchBooks("Spring", "Craig");
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
    }
}
