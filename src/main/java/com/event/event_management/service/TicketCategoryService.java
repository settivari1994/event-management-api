package com.event.event_management.service;

import com.event.event_management.dto.TicketCategoryRequest;
import com.event.event_management.entity.Event;
import com.event.event_management.entity.TicketCategory;
import com.event.event_management.repository.EventRepository;
import com.event.event_management.repository.TicketCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketCategoryService {

    @Autowired
    private TicketCategoryRepository categoryRepository;

    @Autowired
    private EventRepository eventRepository;

    // ✅ CREATE CATEGORY
    public TicketCategory createCategory(Long eventId, TicketCategoryRequest request) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        TicketCategory category = new TicketCategory();

        category.setName(request.getName());
        category.setPrice(request.getPrice());

        category.setTotalQuantity(request.getTotalQuantity());

        // 🔥 IMPORTANT LOGIC
        category.setRemainingQuantity(request.getTotalQuantity());

        category.setValidTill(request.getValidTill());

        category.setEvent(event);
        category.setActive(true);

        return categoryRepository.save(category);
    }

    // ✅ GET CATEGORIES
    public List<TicketCategory> getCategories(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return categoryRepository.findByEventId(event.getId());
    }

    // ✅ UPDATE CATEGORY
    public TicketCategory updateCategory(Long eventId, Long categoryId, TicketCategoryRequest request) {

        TicketCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (!category.getEvent().getId().equals(eventId)) {
            throw new RuntimeException("Category does not belong to this event");
        }

        category.setName(request.getName());
        category.setPrice(request.getPrice());
        category.setTotalQuantity(request.getTotalQuantity());
        category.setValidTill(request.getValidTill());

        // ⚠️ IMPORTANT RULE:
        // Do NOT reset remainingQuantity here
        // because tickets may already be sold

        return categoryRepository.save(category);
    }

    // ✅ DELETE CATEGORY
    public void deleteCategory(Long eventId, Long categoryId) {

        TicketCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (!category.getEvent().getId().equals(eventId)) {
            throw new RuntimeException("Category does not belong to this event");
        }

        categoryRepository.delete(category);
    }
}