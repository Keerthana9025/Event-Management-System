package com.example.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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


}
