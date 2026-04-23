package com.event.event_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.event_management.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findByEventId(Long eventId);

    List<Coupon> findByEventIdAndActiveTrue(Long eventId);
    
    Optional<Coupon> findByCode(String code); // ✅ ADD THIS


    Optional<Coupon> findByCodeAndEventIdAndActiveTrue(String code, Long eventId);
}