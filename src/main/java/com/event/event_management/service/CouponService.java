package com.event.event_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.event_management.dto.CouponRequest;
import com.event.event_management.entity.Coupon;
import com.event.event_management.entity.Event;
import com.event.event_management.repository.CouponRepository;
import com.event.event_management.repository.EventRepository;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepo;

    @Autowired
    private EventRepository eventRepo;

    // ✅ Create Coupon
    public Coupon createCoupon(Long eventId, CouponRequest request) {

        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(request.getCode());
        coupon.setDiscountPercentage(request.getDiscountPercentage());
        coupon.setValidTill(request.getValidTill());
        coupon.setEvent(event);

        return couponRepo.save(coupon);
    }

    // ✅ Get Coupons
    public List<Coupon> getCoupons(Long eventId) {
        return couponRepo.findByEventId(eventId);
    }

    // ✅ Update Coupon
    public Coupon updateCoupon(Long id, CouponRequest request) {

        Coupon coupon = couponRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        coupon.setCode(request.getCode());
        coupon.setDiscountPercentage(request.getDiscountPercentage());
        coupon.setValidTill(request.getValidTill());

        return couponRepo.save(coupon);
    }

    // ✅ Soft Delete
    public void deleteCoupon(Long id) {
        Coupon coupon = couponRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        coupon.setActive(false);
        couponRepo.save(coupon);
    }
}