package com.ticket_booking.ticket_booking_system.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String event;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = true)
    private Vendor vendor;

}
