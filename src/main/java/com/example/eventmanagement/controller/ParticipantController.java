package com.example.eventmanagement.controller;

import com.example.eventmanagement.entity.Participant;
import com.example.eventmanagement.entity.Registration;
import com.example.eventmanagement.service.ParticipantService;
import com.example.eventmanagement.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;
    private final RegistrationService registrationService;

    public ParticipantController(ParticipantService participantService, RegistrationService registrationService) {
        this.participantService = participantService;
        this.registrationService = registrationService;
    }

    // CRUD participants
    @PostMapping
    public ResponseEntity<Participant> create(@Valid @RequestBody Participant p) {
        return ResponseEntity.ok(participantService.create(p));
    }

    @GetMapping
    public ResponseEntity<List<Participant>> list() {
        return ResponseEntity.ok(participantService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> get(@PathVariable Long id) {
        return ResponseEntity.ok(participantService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> update(@PathVariable Long id, @Valid @RequestBody Participant p) {
        return ResponseEntity.ok(participantService.update(id, p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        participantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Registrations
    @PostMapping("/{participantId}/registrations/{eventId}")
    public ResponseEntity<Registration> register(@PathVariable Long participantId, @PathVariable Long eventId) {
        return ResponseEntity.ok(registrationService.register(participantId, eventId));
    }

    @GetMapping("/{participantId}/events")
    public ResponseEntity<List<Registration>> registeredEvents(@PathVariable Long participantId) {
        return ResponseEntity.ok(registrationService.registrationsByParticipant(participantId));
    }

    @DeleteMapping("/registrations/{registrationId}")
    public ResponseEntity<Void> unregister(@PathVariable Long registrationId) {
        registrationService.unregister(registrationId);
        return ResponseEntity.noContent().build();
    }
}
