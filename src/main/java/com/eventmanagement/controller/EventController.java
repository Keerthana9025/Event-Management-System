package com.eventmanagement.controller;

import com.eventmanagement.model.Event;
import com.eventmanagement.service.EventService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class
EventController {

    private final EventService eventService;
    public EventController(EventService eventService) { this.eventService = eventService; }

    @PostMapping(value = "/events", consumes = "application/json", produces = "application/json")
    public Event createEvent(@Valid @RequestBody Event event) {
        return eventService.createEvent(event);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }


    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false, name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false, name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        if (category != null) return ResponseEntity.ok(eventService.getEventsByCategory(category));
        if (date != null) return ResponseEntity.ok(eventService.getEventsByDate(date));
        if (start != null && end != null) return ResponseEntity.ok(eventService.getEventsBetween(start, end));
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @Valid @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

}
