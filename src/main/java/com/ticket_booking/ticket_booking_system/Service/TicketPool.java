package com.ticket_booking.ticket_booking_system.Service;

import com.ticket_booking.ticket_booking_system.Model.Ticket;
import com.ticket_booking.ticket_booking_system.Model.Vendor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TicketPool {

    // Thread-safe list to store WebSocket sessions
    private final List<WebSocketSession> sessions = Collections.synchronizedList(new ArrayList<>());
    private final List<Ticket> ticketList = Collections.synchronizedList(new ArrayList<>());
    private int customerBoughtTickets;
    private int totalticket ;

    // Register a new WebSocket session to listen for updates
    public void registerSession(WebSocketSession session) {
        sessions.add(session);
    }

    // Broadcast a message to all connected WebSocket clients
    public void broadcastUpdate(String message) {
        synchronized (sessions) {
            sessions.forEach(session -> {
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    // Method to add tickets to the pool
    public synchronized void addTicket(int ticketsToRelease, int maximumCapacity, int vendorId, int releaseInterval, Vendor vendor,int totaltickets) {
        totalticket = totaltickets;
        for (int i = 0; i < ticketsToRelease; i++) {
            if (ticketList.size() <= maximumCapacity) {
                try {
                    Ticket ticket = new Ticket(1, "Spandana" + vendorId, BigDecimal.valueOf(100), vendor);
                    ticketList.add(ticket);
                    broadcastUpdate("Ticket added by Vendor " + vendorId + " with ticket ID: " + ticket.getId());
                    System.out.println("Vendor " + vendorId + " released ticket ID: " + ticket.getId() + " " + ticketList.size());
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
                    Thread.currentThread().interrupt(); // Restore interrupt status
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Method to handle ticket purchase
    public synchronized void buyTicket(int ticketsToBuy, int customerId, int retrievalInterval) {
        for (int i = 0; i < ticketsToBuy; i++) {
            try {
                if (ticketList.isEmpty()) {
                    System.out.println("Customer " + customerId + " found no tickets available.");
                    wait();
                } else {
                    Ticket ticket = ticketList.remove(0);
                    System.out.println("Customer " + customerId + " purchased ticket ID: " + ticket);
                    customerBoughtTickets++;
                    broadcastUpdate("Ticket bought by Customer " + customerId + " with ticket ID: " + ticket.getId());
                    notifyAll();
                }
                Thread.sleep(retrievalInterval * 1000); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Customer " + customerId + " interrupted.");
            }
        }
    }

    // Get the current ticket count
    public synchronized int getTicketCount() {
        return ticketList.size();
    }

    // Get the number of tickets bought by customers
    public synchronized int getCustomerBoughtTicketCount() {
        return customerBoughtTickets;
    }
    public int getTotalTicketCount(){
        return totalticket;
    }
}
