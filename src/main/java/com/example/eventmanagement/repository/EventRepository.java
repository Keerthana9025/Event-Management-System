package com.example.eventmanagement.repository;


import com.example.eventmanagement.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCategoryIgnoreCase(String category);
    List<Event> findByDate(LocalDate date);
    List<Event> findByDateBetween(LocalDate start, LocalDate end);
}
