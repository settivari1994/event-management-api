 package com.event.event_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.event.event_management.entity.EventPaymentConfig;
import com.event.event_management.service.EventConfigService;

import java.math.BigDecimal;
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
   
	@PostMapping("/{eventId}/service-charge")
	public ResponseEntity<EventPaymentConfig> setAppServiceCharge(@PathVariable Long eventId,
			@RequestBody Map<String, BigDecimal> request) {
		return ResponseEntity.ok(configService.setAppServiceCharge(eventId, request.get("serviceCharge")));
	}
	
	@GetMapping("/{eventId}/service-charge")
	public ResponseEntity<BigDecimal> getAppServiceCharge(@PathVariable Long eventId) {
		return ResponseEntity.ok(configService.getAppServiceCharge(eventId));
	}	
	
	@PostMapping("/{eventId}/gst")
	public ResponseEntity<EventPaymentConfig> setGst(@PathVariable Long eventId, @RequestBody Integer gstPercentage) {
		return ResponseEntity.ok(configService.setGst(eventId, gstPercentage));
	}
	
	@GetMapping("/{eventId}/gst")
	public ResponseEntity<Integer> getGst(@PathVariable Long eventId) {
		return ResponseEntity.ok(configService.getGst(eventId));
	}	
	
	
}