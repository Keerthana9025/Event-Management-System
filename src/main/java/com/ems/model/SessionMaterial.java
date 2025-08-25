package com.ems.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "session_materials")
public class SessionMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private MaterialType type;
    private String url;

    @ManyToOne
    @JoinColumn(name = "session_id")
    @JsonBackReference
    private Session session;
}
