package com.example.library.library_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "\\d{10}|\\d{13}", message = "ISBN must be 10 or 13 digits")
    private String isbn;

    @NotNull(message = "Publication year is required")
    @Min(value = 1800, message = "Year must be later than 1500")
    @Max(value = 2025, message = "Year must not be in the future")
    private Integer publicationYear;

    private String description; // Used for AI insights

    public Book() {
    }

    public Book(Long id, String title, String author, String isbn, Integer publicationYear, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Author is required") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "Author is required") String author) {
        this.author = author;
    }

    public @NotBlank(message = "ISBN is required") String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotBlank(message = "ISBN is required") String isbn) {
        this.isbn = isbn;
    }

    public @NotNull(message = "Publication year is required") Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(@NotNull(message = "Publication year is required") Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

