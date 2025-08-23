package com.example.eventmanagement.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ManyToMany(mappedBy = "participants")
    private Set<Event> events = new HashSet<>();

}
