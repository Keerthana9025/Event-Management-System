package com.example.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
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
