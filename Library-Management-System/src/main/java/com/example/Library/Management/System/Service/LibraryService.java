package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Repository.Repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //bean
public class LibraryService {

    // fields injected via constructor-based dependency injection.
    private final Repositories.AuthorRepository authorRepository;
    private final Repositories.BookRepository bookRepository;
    private final Repositories.PatronRepository patronRepository;

    @Autowired // annotation on the constructor indicates that Spring will automatically inject
    // instances of the 3 fields when creating LibraryService bean
    public LibraryService(Repositories.AuthorRepository authorRepository, Repositories.BookRepository bookRepository, Repositories.PatronRepository patronRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }
}
// This class would typically contain methods to perform CRUD operations on authors, books, and patrons, as well as any other business logic related to library management
