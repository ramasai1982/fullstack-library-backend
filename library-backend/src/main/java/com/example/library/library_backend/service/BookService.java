package com.example.library.library_backend.service;

import com.example.library.library_backend.model.Book;
import com.example.library.library_backend.repo.BookRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 1. Add Book
    public Book createBook(@Valid Book book) {
        return bookRepository.save(book);
    }

    // 2. Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 3. Get book by ID
    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with ID: " + id)));
    }

    // 4. Update book by ID
    public Optional<Book> updateBook(Long id, @Valid Book updatedBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setIsbn(updatedBook.getIsbn());
            book.setPublicationYear(updatedBook.getPublicationYear());
            book.setDescription(updatedBook.getDescription());
            return bookRepository.save(book);
        });
    }

    // 5. Delete book by ID
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 6. Search book by Title, Author or Title and Author
    public List<Book> searchBooks(String title, String author) {
        if (title != null && author != null) {
            return bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author);
        } else if (title != null) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        } else if (author != null) {
            return bookRepository.findByAuthorContainingIgnoreCase(author);
        } else {
            return bookRepository.findAll(); // if no param then all books are returned
        }
    }
}

