package com.example.eventmanagement.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String speaker;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


}
