package com.eventmanagement.repository;

import com.eventmanagement.model.SessionMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionMaterialRepository extends JpaRepository<SessionMaterial, Long> {
    List<SessionMaterial> findBySessionId(Long sessionId);
}
