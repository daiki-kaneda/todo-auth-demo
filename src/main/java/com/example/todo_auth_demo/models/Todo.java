package com.example.todo_auth_demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "user_uid")
    private User user;
}
