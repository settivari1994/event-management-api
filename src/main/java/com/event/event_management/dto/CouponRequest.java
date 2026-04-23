package com.event.event_management.dto;

import java.time.LocalDateTime;

public class CouponRequest {
	
	private String code;
    private double discountPercentage;
    private LocalDateTime validTill;
    public int getMaxUsagePerUser() {
		return maxUsagePerUser;
	}
	public void setMaxUsagePerUser(int maxUsagePerUser) {
		this.maxUsagePerUser = maxUsagePerUser;
	}
	private int maxUsagePerUser;

    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public LocalDateTime getValidTill() {
		return validTill;
	}
	public void setValidTill(LocalDateTime validTill) {
		this.validTill = validTill;
	}


}