package com.event.event_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.event_management.entity.Event;
import com.event.event_management.entity.User;
import com.event.event_management.repository.EventRepository;
import com.event.event_management.repository.UserRepository;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Event
    public Event createEvent(Event event) {
        event.setAvailableTickets(event.getTotalTickets());
        return eventRepository.save(event);
    }

    // Assign Event
    public Event assignEvent(Long eventId, Long agentId) {

        Event event = eventRepository.findById(eventId).orElseThrow();
        User agent = userRepository.findById(agentId).orElseThrow();

        event.setAssignedAgent(agent);

        return eventRepository.save(event);
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get agent events
    public List<Event> getAgentEvents(Long agentId) {
        return eventRepository.findByAssignedAgentId(agentId);
    }
    
    public Event updateEvent(Long id, Event updatedEvent) {

        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        existing.setName(updatedEvent.getName());
        existing.setLocation(updatedEvent.getLocation());
        existing.setPrice(updatedEvent.getPrice());
        existing.setTotalTickets(updatedEvent.getTotalTickets());
        existing.setAvailableTickets(updatedEvent.getAvailableTickets());
        existing.setEventDate(updatedEvent.getEventDate());

        return eventRepository.save(existing);
    }
    
    public void deleteEvent(Long id) {

        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        eventRepository.delete(existing);
    }
    
    public List<Event> getEventsByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return eventRepository.findByAssignedAgentId(user.getId());
    }
}