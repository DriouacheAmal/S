package com.example.Library.Management.System.Entity;

import com.example.Library.Management.System.Entity.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import org.springframework.data.annotation.Id;

import java.util.Set;
@Entity
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToMany  //relationship between Patron and Book
    private Set<Book> borrowedBooks; //the books borrowed by the patron

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
