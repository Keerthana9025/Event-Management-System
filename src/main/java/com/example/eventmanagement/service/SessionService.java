package com.example.eventmanagement.service;

import com.example.eventmanagement.entity.Event;
import com.example.eventmanagement.entity.Session;
import com.example.eventmanagement.exception.ResourceNotFoundException;
import com.example.eventmanagement.repository.EventRepository;
import com.example.eventmanagement.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final EventRepository eventRepository;

    public SessionService(SessionRepository sessionRepository, EventRepository eventRepository) {
        this.sessionRepository = sessionRepository;
        this.eventRepository = eventRepository;
    }

    public Session createSession(Long eventId, Session session) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));
        session.setEvent(event);
        return sessionRepository.save(session);
    }

    public Session getSession(Long sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + sessionId));
    }

    public List<Session> getSessionsByEvent(Long eventId) {
        return sessionRepository.findByEventId(eventId);
    }

    public Session updateSession(Long sessionId, Session data) {
        Session s = getSession(sessionId);
        s.setTopic(data.getTopic());
        s.setSpeaker(data.getSpeaker());
        s.setStartTime(data.getStartTime());
        s.setEndTime(data.getEndTime());
        return sessionRepository.save(s);
    }

    public void deleteSession(Long sessionId) {
        Session s = getSession(sessionId);
        sessionRepository.delete(s);
    }
}
