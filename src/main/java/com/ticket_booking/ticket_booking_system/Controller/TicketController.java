package com.ticket_booking.ticket_booking_system.Controller;

import com.ticket_booking.ticket_booking_system.Model.Configuration;
import com.ticket_booking.ticket_booking_system.Service.TicketPool;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@CrossOrigin
public class TicketController {

    @Autowired
    private TicketPool ticketPool;// Injected TicketPool
    private Configuration configuration ;


    /**
     * Endpoint to fetch the count of available tickets.
     *
     * @return The count of tickets available in the pool.
     */
    @GetMapping("/count")
    public ResponseEntity<Integer> getTicketCount() {
        int availableTickets = ticketPool.getTicketCount();
        return ResponseEntity.ok(availableTickets);
    }
    // Fetch customer bought ticket count
    @GetMapping("/bought")
    public ResponseEntity<Integer> getCustomerBoughtCount() {
        return ResponseEntity.ok(ticketPool.getCustomerBoughtTicketCount());
    }
    // Fetch total ticket count
    @GetMapping("/total")
    public ResponseEntity<Integer> getTotalTicketCount() {
        return ResponseEntity.ok(ticketPool.getTotalTicketCount());
    }
}
