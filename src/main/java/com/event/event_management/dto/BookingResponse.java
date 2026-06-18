package com.event.event_management.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BookingResponse {

    private Long bookingId;
    private String customerName;
    private String customerPhone;
    
	private double discount;
    private double finalAmount;

    private double totalAmount;
    private String paymentStatus;
    private String paymentMethod;
    
    public Double getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(Double gstAmount) {
		this.gstAmount = gstAmount;
	}

	public Double getAppServiceCharge() {
		return appServiceCharge;
	}

	public void setAppServiceCharge(Double appServiceCharge) {
		this.appServiceCharge = appServiceCharge;
	}

	private Double gstAmount;

    private Double appServiceCharge;

    private LocalDateTime bookingTime;
    
    private EventSummaryResponse event;


    private List<BookingItemResponse> items;

    // ✅ Getters & Setters
    
    public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public List<BookingItemResponse> getItems() {
        return items;
    }

    public void setItems(List<BookingItemResponse> items) {
        this.items = items;
    }

	public EventSummaryResponse getEvent() {
		return event;
	}

	public void setEvent(EventSummaryResponse event) {
		this.event = event;
	}
}
