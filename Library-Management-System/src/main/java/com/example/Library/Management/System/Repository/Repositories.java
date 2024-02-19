package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entity.Author;
import com.example.Library.Management.System.Entity.Book;
import com.example.Library.Management.System.Entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public class Repositories {

    public interface AuthorRepository extends JpaRepository<Author, Long> {
    } // AuthorRepository is an interface that extends JpaRepository
    // JpaRepository is a Spring Data JPA interface that provides
    // CRUD (Create, Read, Update, Delete) operations for the specified entity type (Author)
    // with the primary key of type Long
    // by extending JpaRepository, AuthorRepository inherits methods for performing common database operations
    // like saving, updating, deleting, and querying Author entities


    public interface BookRepository extends JpaRepository<Book, Long> {
    }
    public interface PatronRepository extends JpaRepository<Patron, Long> {
    }



}
