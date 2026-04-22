package com.event.event_management.controller;

import com.event.event_management.dto.TicketCategoryRequest;
import com.event.event_management.entity.TicketCategory;
import com.event.event_management.service.TicketCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/events/{eventId}/categories")
public class TicketCategoryController {

    @Autowired
    private TicketCategoryService categoryService;

    // ✅ CREATE CATEGORY
    @PostMapping
    public TicketCategory create(
            @PathVariable Long eventId,
            @RequestBody TicketCategoryRequest request) {

        return categoryService.createCategory(eventId, request);
    }

    // ✅ GET CATEGORIES
    @GetMapping
    public List<TicketCategory> get(@PathVariable Long eventId) {
        return categoryService.getCategories(eventId);
    }

    // ✅ UPDATE CATEGORY
    @PutMapping("/{categoryId}")
    public TicketCategory update(
            @PathVariable Long eventId,
            @PathVariable Long categoryId,
            @RequestBody TicketCategoryRequest request) {

        return categoryService.updateCategory(eventId, categoryId, request);
    }

    // ✅ DELETE CATEGORY
    @DeleteMapping("/{categoryId}")
    public String delete(
            @PathVariable Long eventId,
            @PathVariable Long categoryId) {

        categoryService.deleteCategory(eventId, categoryId);
        return "Category deleted";
    }
}