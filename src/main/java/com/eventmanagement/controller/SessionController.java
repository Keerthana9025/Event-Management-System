package com.eventmanagement.controller;

import com.eventmanagement.model.Session;
import com.eventmanagement.service.SessionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events/{eventId}/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) { this.sessionService = sessionService; }

    @PostMapping
    public Session createSession(@PathVariable Long eventId, @RequestBody Session session) {
        return sessionService.createSession(eventId, session);
    }


    @GetMapping
    public ResponseEntity<List<Session>> list(@PathVariable Long eventId) {
        return ResponseEntity.ok(sessionService.getSessionsByEvent(eventId));
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<Session> get(@PathVariable Long sessionId) {
        return ResponseEntity.ok(sessionService.getSession(sessionId));
    }

    @PutMapping("/{sessionId}")
    public ResponseEntity<Session> update(@PathVariable Long sessionId, @Valid @RequestBody Session session) {
        return ResponseEntity.ok(sessionService.updateSession(sessionId, session));
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Void> delete(@PathVariable Long sessionId) {
        sessionService.deleteSession(sessionId);
        return ResponseEntity.noContent().build();
    }
}
