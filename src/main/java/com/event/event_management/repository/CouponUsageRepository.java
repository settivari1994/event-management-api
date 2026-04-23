package com.event.event_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.event_management.entity.CouponUsage;

public interface CouponUsageRepository extends JpaRepository<CouponUsage, Long> {
	
    Optional<CouponUsage> findByPhoneAndCouponCode(String phone, String couponCode);


}
