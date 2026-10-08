package com.event.event_management.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
public class EventPaymentConfig {

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String upiId;
    


	public Integer getGstPercentage() {
		return gstPercentage;
	}

	public void setGstPercentage(Integer gstPercentage) {
		this.gstPercentage = gstPercentage;
	}

	@Column(name = "service_charge_percentage")
	private BigDecimal serviceChargePercentage;

    public BigDecimal getServiceChargePercentage() {
		return serviceChargePercentage;
	}

	public void setServiceChargePercentage(BigDecimal serviceChargePercentage) {
		this.serviceChargePercentage = serviceChargePercentage;
	}

	@Column(name="gst_percentage")
    private Integer gstPercentage;

    @OneToOne
    @JoinColumn(name = "event_id", unique = true)
    private Event event;
    
    

}