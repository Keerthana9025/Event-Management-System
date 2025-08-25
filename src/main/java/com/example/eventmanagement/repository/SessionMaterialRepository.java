package com.example.eventmanagement.repository;

import com.example.eventmanagement.entity.SessionMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionMaterialRepository extends JpaRepository<SessionMaterial, Long> {
    List<SessionMaterial> findBySessionId(Long sessionId);
}
