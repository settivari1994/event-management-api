package com.event.event_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.event_management.dto.CouponRequest;
import com.event.event_management.entity.Coupon;
import com.event.event_management.entity.CouponUsage;
import com.event.event_management.entity.Event;
import com.event.event_management.repository.CouponRepository;
import com.event.event_management.repository.CouponUsageRepository;
import com.event.event_management.repository.EventRepository;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepo;

    @Autowired
    private EventRepository eventRepo;
    
    @Autowired
    private CouponUsageRepository couponUsageRepo;

    // ✅ Create Coupon
 public Coupon createCoupon(Long eventId, CouponRequest request) {

    Event event = eventRepo.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));

    Coupon coupon = new Coupon();
    coupon.setCode(request.getCode());
    coupon.setDiscountPercentage(request.getDiscountPercentage());
    coupon.setValidTill(request.getValidTill());
    coupon.setMaxUsagePerUser(request.getMaxUsagePerUser()); // ✅ NEW
    coupon.setEvent(event);

    return couponRepo.save(coupon);
}
 
 public Coupon getCouponByCode(String code) {
	    return couponRepo.findByCode(code)
	            .orElseThrow(() -> new RuntimeException("Coupon not found"));
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
        coupon.setMaxUsagePerUser(request.getMaxUsagePerUser()); // ✅ NEW

        return couponRepo.save(coupon);
    }

    // ✅ Soft Delete
    public void deleteCoupon(Long id) {
        Coupon coupon = couponRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        coupon.setActive(false);
        couponRepo.save(coupon);
    }
    
    
    public void validateAndApplyCoupon(String phone, String couponCode) {

        Coupon coupon = couponRepo.findByCode(couponCode)
                .orElseThrow(() -> new RuntimeException("Invalid coupon"));

        if (!coupon.isActive()) {
            throw new RuntimeException("Coupon is inactive");
        }

        if (coupon.getValidTill().toLocalDate().isBefore(java.time.LocalDate.now())) {
            throw new RuntimeException("Coupon expired");
        }

        CouponUsage usage = couponUsageRepo
                .findByPhoneAndCouponCode(phone, couponCode)
                .orElse(null);

        if (usage == null) {
            usage = new CouponUsage();
            usage.setPhone(phone);
            usage.setCouponCode(couponCode);
            usage.setUsageCount(1);
        } else {
            if (usage.getUsageCount() >= coupon.getMaxUsagePerUser()) {
                throw new RuntimeException("Coupon usage limit exceeded for this phone number");
            }
            usage.setUsageCount(usage.getUsageCount() + 1);
        }

        couponUsageRepo.save(usage);
    }
    
}