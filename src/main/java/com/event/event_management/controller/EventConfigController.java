package com.event.event_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.event.event_management.service.EventConfigService;

import java.util.Map;

@RestController
@RequestMapping("/api/event-config")
@CrossOrigin(origins = "*")

public class EventConfigController {

    @Autowired
    private EventConfigService configService;

    // ✅ ADMIN → set UPI
    @PostMapping("/{eventId}/upi")
    public String setUpi(@PathVariable Long eventId,
                         @RequestBody Map<String, String> request) {

        configService.setUpi(eventId, request.get("upiId"));
        return "UPI configured successfully";
    }

    // ✅ UI → get UPI
    @GetMapping("/{eventId}/upi")
    public String getUpi(@PathVariable Long eventId) {
        return configService.getUpiByEvent(eventId);
    }
}