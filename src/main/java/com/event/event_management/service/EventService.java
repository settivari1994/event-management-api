package com.event.event_management.service;

import com.event.event_management.entity.Event;
import com.event.event_management.entity.Role;
import com.event.event_management.entity.User;
import com.event.event_management.repository.EventRepository;
import com.event.event_management.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Create Event (Admin only)
    public Event createEvent(Event event) {
        event.setEventCode(generateUniqueEventCode());
        return eventRepository.save(event);
    }

    // ✅ Get All Events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // 🔥 ✅ Assign Organisers
    public Event assignOrganisers(Long eventId, List<Long> userIds) {

        System.out.println("➡ Assigning organisers: " + userIds);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        List<User> users = userRepository.findAllById(userIds);

        if (users.isEmpty()) {
            throw new RuntimeException("No users found for given IDs");
        }

        // ✅ Validate role
        for (User user : users) {
            if (user.getRole() != Role.ORGANIZER) {
                throw new RuntimeException("User is not an organiser: " + user.getId());
            }
        }

        event.setOrganisers(users);

        Event savedEvent = eventRepository.save(event);

        System.out.println("✅ Organisers assigned successfully");

        return savedEvent;
    }

    // ✅ Get Event by Code
    public Event getEventByCode(String code) {
        return eventRepository.findByEventCode(code)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    // ✅ Delete Event
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    // 🔥 Generate 5-digit unique code
    private String generateUniqueEventCode() {
        String code;
        Random random = new Random();

        do {
            code = String.valueOf(10000 + random.nextInt(90000));
        } while (eventRepository.existsByEventCode(code));

        return code;
    }
    
    
    public List<Event> getEventsByOrganiser(String username) {
        User organiser = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return eventRepository.findByOrganisersContaining(organiser);
    }
    
}