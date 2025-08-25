package com.ems.service;

import com.ems.exception.ResourceNotFoundException;
import com.ems.model.Event;
import com.ems.model.Participant;
import com.ems.model.Registration;
import com.ems.repository.EventRepository;
import com.ems.repository.ParticipantRepository;
import com.ems.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;

    public RegistrationService(RegistrationRepository registrationRepository,
                               ParticipantRepository participantRepository,
                               EventRepository eventRepository) {
        this.registrationRepository = registrationRepository;
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
    }

    public Registration register(Long participantId, Long eventId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + participantId));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));

        if (registrationRepository.existsByParticipantIdAndEventId(participantId, eventId)) {
            throw new IllegalArgumentException("Participant already registered for this event");
        }

        Registration r = new Registration();
        r.setParticipant(participant);
        r.setEvent(event);
        return registrationRepository.save(r);
    }

    public List<Registration> registrationsByParticipant(Long participantId) {
        return registrationRepository.findByParticipantId(participantId);
    }

    public List<Registration> registrationsByEvent(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    public void unregister(Long registrationId) {
        Registration r = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with id: " + registrationId));
        registrationRepository.delete(r);
    }
}
