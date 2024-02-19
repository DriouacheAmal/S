package Entities;

import Entities.Enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.h2.engine.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.ObjectInputFilter;
import java.util.Date;

@Data
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Check if null
    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "description")
    private String description;

    //Change to enum
    //Check if null
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private String status;

    //Check if null
    //Check if date is valid (due date in the future)
    //@Future
    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

}
