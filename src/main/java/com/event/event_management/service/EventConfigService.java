package com.event.event_management.service;


import java.math.BigDecimal;

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
    
    
    public EventPaymentConfig setServiceChargePercentage(
            Long eventId,
            BigDecimal percentage) {

        if (percentage == null) {
            throw new RuntimeException("Service charge percentage is required");
        }

        if (percentage.compareTo(BigDecimal.ZERO) < 0 ||
            percentage.compareTo(new BigDecimal("100")) > 0) {
            throw new RuntimeException(
                    "Service charge percentage must be between 0 and 100"
            );
        }

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        EventPaymentConfig config = configRepository
                .findByEventId(eventId)
                .orElse(new EventPaymentConfig());

        config.setEvent(event);
        config.setServiceChargePercentage(percentage);

        return configRepository.save(config);
    }
	
    public BigDecimal getServiceChargePercentage(Long eventId) {
        return configRepository.findByEventId(eventId)
                .map(EventPaymentConfig::getServiceChargePercentage)
                .orElse(BigDecimal.ZERO);
    }
	
	
	public EventPaymentConfig setGst(Long eventId, Integer gstPercentage) {
		Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
		EventPaymentConfig config = configRepository.findByEventId(eventId).orElse(new EventPaymentConfig());
		config.setEvent(event);
		config.setGstPercentage(gstPercentage);
		return configRepository.save(config);
	}
	
	public Integer getGst(Long eventId) {
		return configRepository.findByEventId(eventId).map(EventPaymentConfig::getGstPercentage).orElse(null);
	}
	
}