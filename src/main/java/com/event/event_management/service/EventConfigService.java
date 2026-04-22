package com.event.event_management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.event_management.entity.Event;
import com.event.event_management.entity.EventPaymentConfig;
import com.event.event_management.repository.EventConfigRepository;
import com.event.event_management.repository.EventRepository;

@Service
public class EventConfigService {

    @Autowired
    private EventConfigRepository configRepository;

    @Autowired
    private EventRepository eventRepository;

    // ✅ Admin sets UPI
    public EventPaymentConfig setUpi(Long eventId, String upiId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        EventPaymentConfig config = configRepository.findByEventId(eventId)
                .orElse(new EventPaymentConfig());

        config.setEvent(event);
        config.setUpiId(upiId);

        return configRepository.save(config);
    }

    // ✅ UI fetches UPI
    public String getUpiByEvent(Long eventId) {
        return configRepository.findByEventId(eventId)
                .map(EventPaymentConfig::getUpiId)
                .orElse(null);
    }
}