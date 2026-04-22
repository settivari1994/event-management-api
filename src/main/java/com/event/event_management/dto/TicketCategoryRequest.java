package com.event.event_management.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class TicketCategoryRequest {

    @NotBlank(message = "Category name is required")
    private String name;

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Total quantity is required")
    private Integer totalQuantity;
    
    
    private int remainingQuantity;


    public int getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	private LocalDateTime validTill;

    // getters & setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public LocalDateTime getValidTill() {
        return validTill;
    }

    public void setValidTill(LocalDateTime validTill) {
        this.validTill = validTill;
    }
}