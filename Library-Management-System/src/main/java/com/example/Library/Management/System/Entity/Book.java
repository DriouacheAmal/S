package com.example.Library.Management.System.Entity;

import com.example.Library.Management.System.Entity.Author;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Date;
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;
    private Date publicationDate;

    @ManyToOne // annotations establish a many-to-one relationship between Book and Author
    // which means many books can be written by one author
    @JoinColumn(name = "author_id") //annotation specifies the name of the foreign key column in the Book table
    // that refers to the id column in the Author table
    private Author author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
