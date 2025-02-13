package com.example.library.library_backend.repo;


import com.example.library.library_backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Search by Title - case ignored
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Search by Author - case ignored
    List<Book> findByAuthorContainingIgnoreCase(String author);

    // Search by Title and Author - case ignored
    List<Book> findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);
}

