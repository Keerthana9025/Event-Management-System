package com.ems.controller;

import com.ems.model.SessionMaterial;
import com.ems.service.SessionMaterialService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events/{eventId}/sessions/{sessionId}/materials")
public class SessionMaterialController {

    private final SessionMaterialService materialService;

    public SessionMaterialController(SessionMaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping
    public ResponseEntity<SessionMaterial> add(@PathVariable Long sessionId, @Valid @RequestBody SessionMaterial material) {
        return ResponseEntity.ok(materialService.addMaterial(sessionId, material));
    }

    @GetMapping
    public ResponseEntity<List<SessionMaterial>> list(@PathVariable Long sessionId) {
        return ResponseEntity.ok(materialService.listBySession(sessionId));
    }

    @PutMapping("/{materialId}")
    public ResponseEntity<SessionMaterial> update(@PathVariable Long materialId, @Valid @RequestBody SessionMaterial material) {
        return ResponseEntity.ok(materialService.updateMaterial(materialId, material));
    }

    @DeleteMapping("/{materialId}")
    public ResponseEntity<Void> delete(@PathVariable Long materialId) {
        materialService.deleteMaterial(materialId);
        return ResponseEntity.noContent().build();
    }
}
