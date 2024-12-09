package com.ticket_booking.ticket_booking_system.Service;

import com.ticket_booking.ticket_booking_system.Model.Ticket;
import com.ticket_booking.ticket_booking_system.Model.Vendor;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketPool {

    private final List<Ticket> ticketList = Collections.synchronizedList(new ArrayList<>());

    public synchronized void addTicket(int ticketsToRelease, int maximumCapacity, int vendorId, int releaseInterval, Vendor vendor) {
        for (int i = 0; i < ticketsToRelease; i++) {
            if (ticketList.size() <= maximumCapacity) {
                try {
                    Ticket ticket = new Ticket(1, "Spandana" + vendorId, BigDecimal.valueOf(100), vendor);
                    ticketList.add(ticket);
                    notifyAll();
                    System.out.println("Vendor " + vendorId + " released ticket ID: " + ticket.getId()+ ticketList.size());
                    System.out.println(getTicketCount());
                    Thread.sleep(releaseInterval * 1000); // Simulate delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Vendor " + vendorId + " interrupted.");
                }
            } else {
                System.out.println("Ticket pool is full. Vendor " + vendorId + " is waiting.");
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore the interrupt status
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public synchronized void buyTicket(int ticketsToBuy, int customerId, int retrievalInterval) {
        for (int i = 0; i < ticketsToBuy; i++) {

            try {
                if (ticketList.isEmpty()) {
                    System.out.println("Customer " + customerId + " found no tickets available.");
                    wait();

                } else {
                    Ticket ticket = ticketList.remove(0);
                    System.out.println("Customer " + customerId + " purchased ticket ID: " + ticket);
                    notifyAll();
                }
                Thread.sleep(retrievalInterval * 1000); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Customer " + customerId + " interrupted.");
            }
        }

    }

    @Synchronized
    public int getTicketCount() {
        return ticketList.size();

    }
}

