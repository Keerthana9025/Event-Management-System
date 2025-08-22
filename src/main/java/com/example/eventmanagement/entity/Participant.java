package com.example.eventmanagement.entity;


import jakarta.persistence.*;
import java.util.*;

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToMany(mappedBy = "participants")
    private Set<Event> events = new HashSet<>();
    // Getters & Setters
}
