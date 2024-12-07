package com.ticket_booking.ticket_booking_system.Service;

import com.ticket_booking.ticket_booking_system.Model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketPool {

    private final List<Ticket> ticketList = Collections.synchronizedList(new ArrayList<>());
    private final int maxCapacity = 100; // Example capacity

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (ticketList.size() >= maxCapacity) {
            System.out.println("Ticket pool is full. Waiting to add tickets...");
            wait(); // Wait until space is available
        }
        ticketList.add(ticket);
        System.out.println("Ticket added: " + ticket);
        notifyAll(); // Notify threads waiting to retrieve tickets
    }

    public synchronized Ticket removeTicket() throws InterruptedException {
        while (ticketList.isEmpty()) {
            System.out.println("Ticket pool is empty. Waiting for tickets...");
            wait(); // Wait until a ticket is available
        }
        Ticket ticket = ticketList.remove(0);
        System.out.println("Ticket removed: " + ticket);
        notifyAll(); // Notify threads waiting to add tickets
        return ticket;
    }
}

