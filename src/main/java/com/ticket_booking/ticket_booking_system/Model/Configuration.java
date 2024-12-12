package com.ticket_booking.ticket_booking_system.Model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int numberOfVendors;
    private int numberOfCustomers;
    private String eventName;
    private int ticketPrice;
}
