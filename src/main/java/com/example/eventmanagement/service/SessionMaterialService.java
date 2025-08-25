package com.example.eventmanagement.service;


import com.example.eventmanagement.entity.MaterialType;
import com.example.eventmanagement.entity.Session;
import com.example.eventmanagement.entity.SessionMaterial;
import com.example.eventmanagement.exception.ResourceNotFoundException;
import com.example.eventmanagement.repository.SessionMaterialRepository;
import com.example.eventmanagement.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionMaterialService {

    private final SessionMaterialRepository materialRepository;
    private final SessionRepository sessionRepository;

    public SessionMaterialService(SessionMaterialRepository materialRepository, SessionRepository sessionRepository) {
        this.materialRepository = materialRepository;
        this.sessionRepository = sessionRepository;
    }

    public SessionMaterial addMaterial(Long sessionId, SessionMaterial material) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + sessionId));


        if (material.getType() == MaterialType.PDF
                && !material.getUrl().toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("URL must end with .pdf for PDF type");
        }


        material.setSession(session);
        return materialRepository.save(material);
    }

    public List<SessionMaterial> listBySession(Long sessionId) {
        return materialRepository.findBySessionId(sessionId);
    }

    public SessionMaterial updateMaterial(Long materialId, SessionMaterial data) {
        SessionMaterial m = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found with id: " + materialId));

        m.setTitle(data.getTitle());
        m.setType(data.getType());
        m.setUrl(data.getUrl());
        return materialRepository.save(m);
    }

    public void deleteMaterial(Long materialId) {
        SessionMaterial m = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found with id: " + materialId));
        materialRepository.delete(m);
    }
}
