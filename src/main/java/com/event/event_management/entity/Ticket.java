package com.event.event_management.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double totalPrice;

    private LocalDateTime bookingTime;

    // 🎯 Customer who bought ticket
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    // 🎯 Event booked
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    // 🎯 Organizer who sold it
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;
}
