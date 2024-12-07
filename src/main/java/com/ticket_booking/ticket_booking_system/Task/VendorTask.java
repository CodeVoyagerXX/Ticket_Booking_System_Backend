package com.ticket_booking.ticket_booking_system.Task;

import com.ticket_booking.ticket_booking_system.Model.Ticket;
import com.ticket_booking.ticket_booking_system.Model.Vendor;
import com.ticket_booking.ticket_booking_system.Service.TicketPool;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class VendorTask {

    private final TicketPool ticketPool;

    @Async
    public void releaseTickets(String vendorName, int totalTickets) {
        for (int i = 0; i < totalTickets; i++) {
            try {
                Ticket ticket = new Ticket(0, "Event - Vendor: " + vendorName, BigDecimal.valueOf(50.00),new Vendor());
                ticketPool.addTicket(ticket);
                System.out.println("Vendor " + vendorName + " added ticket: " + ticket);
                Thread.sleep(1000); // Simulate ticket release delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Vendor task interrupted: " + e.getMessage());
            }
        }
    }
}

