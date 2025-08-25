package com.example.eventmanagement.service;

import com.example.eventmanagement.entity.Participant;
import com.example.eventmanagement.exception.ResourceNotFoundException;
import com.example.eventmanagement.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public Participant create(Participant p) { return participantRepository.save(p); }

    public Participant getById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));
    }

    public List<Participant> getAll() { return participantRepository.findAll(); }

    public Participant update(Long id, Participant data) {
        Participant p = getById(id);
        p.setName(data.getName());
        p.setEmail(data.getEmail());
        return participantRepository.save(p);
    }

    public void delete(Long id) {
        participantRepository.delete(getById(id));
    }
}
