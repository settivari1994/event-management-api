package com.event.event_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "coupon_usage",
       uniqueConstraints = @UniqueConstraint(columnNames = {"phone", "couponCode"}))
public class CouponUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    private String couponCode;

    private int usageCount;

    // Getters & Setters
    public Long getId() { return id; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCouponCode() { return couponCode; }
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }

    public int getUsageCount() { return usageCount; }
    public void setUsageCount(int usageCount) { this.usageCount = usageCount; }
}