package com.event.event_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.event_management.dto.CouponRequest;
import com.event.event_management.entity.Coupon;
import com.event.event_management.service.CouponService;

@RestController
@RequestMapping("/api/admin/events/{eventId}/coupons")
@CrossOrigin(origins = "*")

public class CouponController {

    @Autowired
    private CouponService couponService;

    // ✅ Create
    @PostMapping
    public Coupon create(
            @PathVariable Long eventId,
            @RequestBody CouponRequest request) {

        return couponService.createCoupon(eventId, request);
    }

    // ✅ Get
    @GetMapping
    public List<Coupon> get(@PathVariable Long eventId) {
        return couponService.getCoupons(eventId);
    }

    // ✅ Update
    @PutMapping("/{id}")
    public Coupon update(
            @PathVariable Long id,
            @RequestBody CouponRequest request) {

        return couponService.updateCoupon(id, request);
    }

    // ✅ Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return "Coupon deleted";
    }
}