package com.event.event_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.event_management.entity.TicketCategory;

@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Long> {

    List<TicketCategory> findByEventId(Long eventId);

    List<TicketCategory> findByEventIdAndActiveTrue(Long eventId);
    
    List<TicketCategory> findByEventIdAndActiveTrueAndRemainingQuantityGreaterThan(Long eventId, int quantity);
}