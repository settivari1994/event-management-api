package com.event.event_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.event.event_management.entity.EventPaymentConfig;

import java.util.Optional;

public interface EventConfigRepository extends JpaRepository<EventPaymentConfig, Long> {

    Optional<EventPaymentConfig> findByEventId(Long eventId);
}