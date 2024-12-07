package com.ticket_booking.ticket_booking_system.Task;

import com.ticket_booking.ticket_booking_system.Service.TicketPool;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerTask {

    private final TicketPool ticketPool;

    @Async
    public void retrieveTickets(String customerName, int ticketsToRetrieve) {
        for (int i = 0; i < ticketsToRetrieve; i++) {
            try {
                var ticket = ticketPool.removeTicket();
                System.out.println("Customer " + customerName + " retrieved ticket: " + ticket);
                Thread.sleep(500); // Simulate ticket retrieval delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Customer task interrupted: " + e.getMessage());
            }
        }
    }
}

