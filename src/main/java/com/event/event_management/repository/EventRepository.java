package com.event.event_management.repository;

import com.event.event_management.entity.Event;
import com.event.event_management.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByEventCode(String eventCode);

    Optional<Event> findByEventCode(String eventCode);
    
    List<Event> findByOrganisersContaining(User organiser);

}