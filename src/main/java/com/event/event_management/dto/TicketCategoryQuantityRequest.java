package com.event.event_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TicketCategoryQuantityRequest {

    @NotNull(message = "Total quantity is required")
    @Min(value = 1, message = "Total quantity must be at least 1")
    private Integer totalQuantity;

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}