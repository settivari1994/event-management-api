package com.event.event_management.service;

import com.event.event_management.dto.TicketCategoryRequest;
import com.event.event_management.entity.Event;
import com.event.event_management.entity.TicketCategory;
import com.event.event_management.repository.EventRepository;
import com.event.event_management.repository.TicketCategoryRepository;
import com.event.event_management.dto.TicketCategoryQuantityRequest;
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
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        return categoryRepository.findByEventIdAndActiveTrue(event.getId());
    }

 // ✅ UPDATE CATEGORY QUANTITY ONLY

    public TicketCategory updateCategoryQuantity(
            Long eventId,
            Long categoryId,
            TicketCategoryQuantityRequest request) {

        TicketCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Make sure category belongs to this event
        if (!category.getEvent().getId().equals(eventId)) {
            throw new RuntimeException(
                    "Category does not belong to this event"
            );
        }

        int newTotalQuantity = request.getTotalQuantity();

        // Calculate tickets already sold
        int soldQuantity =
                category.getTotalQuantity()
                        - category.getRemainingQuantity();

        // Do not allow quantity below already sold tickets
        if (newTotalQuantity < soldQuantity) {
            throw new RuntimeException(
                    "Total quantity cannot be less than tickets already sold: "
                            + soldQuantity
            );
        }

        // Calculate new remaining quantity
        int newRemainingQuantity =
                newTotalQuantity - soldQuantity;

        category.setTotalQuantity(newTotalQuantity);
        category.setRemainingQuantity(newRemainingQuantity);

        return categoryRepository.save(category);
    }

    // ✅ DELETE CATEGORY
    public void deleteCategory(Long eventId, Long categoryId) {

        TicketCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Make sure category belongs to this event
        if (!category.getEvent().getId().equals(eventId)) {
            throw new RuntimeException(
                    "Category does not belong to this event"
            );
        }

        // Soft delete
        category.setActive(false);
        categoryRepository.save(category);
    }
}