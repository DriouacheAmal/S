package com.example.Library.Management.System.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

@Entity // It s an annotation that marks the class as a JPA entity,
// indicating that it will be mapped to a table in the database
public class Author {
    @Id // It s the primary key of the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //the strategy used for value generation for
    // the primary key
    // the database will automatically assign an ID.
    private Long id;
// Attributes
    private String name;
    private String biography;

    public Long getId() {
        return id;
    }
//Getters Setters
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
