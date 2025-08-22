package com.example.eventmanagement.entity;

import jakarta.persistence.*;

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String type;
    private String url;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    // Getters & Setters
}
