package com.event.event_management.dto;

import java.util.List;

public class BookingRequest {

    private String customerName;
    private String customerPhone;
    private String couponCode;
    private String paymentMethod;
    private String transactionId;

    private List<CategorySelection> selections;

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

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<CategorySelection> getSelections() {
        return selections;
    }

    public void setSelections(List<CategorySelection> selections) {
        this.selections = selections;
    }
}