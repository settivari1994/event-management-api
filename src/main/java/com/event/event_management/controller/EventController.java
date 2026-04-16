package com.event.event_management.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import com.event.event_management.entity.Event;
import com.event.event_management.service.EventService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }
    
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        return eventService.updateEvent(id, updatedEvent);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "Event deleted successfully";
    }

    @PutMapping("/{eventId}/assign/{agentId}")
    public Event assignEvent(@PathVariable Long eventId,
                             @PathVariable Long agentId) {
        return eventService.assignEvent(eventId, agentId);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/agent/{agentId}")
    public List<Event> getAgentEvents(@PathVariable Long agentId) {
        return eventService.getAgentEvents(agentId);
    }
    
    @GetMapping("/my-events")
    public List<Event> getMyEvents(Authentication authentication) {

        String username = authentication.getName();

        return eventService.getEventsByUsername(username);
    }
}
