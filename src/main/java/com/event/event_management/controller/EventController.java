package com.event.event_management.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.event.event_management.entity.Event;
import com.event.event_management.service.EventService;

import org.springframework.security.core.Authentication;


import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventService eventService;

    // ✅ Create Event
    @PostMapping
    public Event createEvent(@Valid @RequestBody Event event) {
        return eventService.createEvent(event);
    }

    // ✅ Get All Events
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // ✅ Get Event by Code
    @GetMapping("/code/{eventCode}")
    public Event getEventByCode(@PathVariable String eventCode) {
        return eventService.getEventByCode(eventCode);
    }

    // 🔥 ✅ Assign Organisers (ADMIN ONLY)
    @PutMapping("/{eventId}/assign-organisers")
    public Event assignOrganisers(
            @PathVariable Long eventId,
            @RequestBody List<Long> organiserIds) {

        System.out.println("🔥 Assign API called for event: " + eventId);

        return eventService.assignOrganisers(eventId, organiserIds);
    }

    // ✅ Delete Event
    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "Event deleted successfully";
    }
    
    @GetMapping("/assigned")
    public List<Event> getAssignedEvents(Authentication authentication) {
        String username = authentication.getName();
        return eventService.getEventsByOrganiser(username);
    }
    

}