package com.eventmanagement.repository;

import com.eventmanagement.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByParticipantId(Long participantId);
    List<Registration> findByEventId(Long eventId);
    boolean existsByParticipantIdAndEventId(Long participantId, Long eventId);
}
