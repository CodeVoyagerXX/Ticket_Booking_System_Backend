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
    private volatile boolean simulationRunning = true;

    // Register a new WebSocket session to listen for updates
    public void registerSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }
    public void setSimulationRunning(boolean running) {
        this.simulationRunning = running;
    }

    public boolean isSimulationRunning() {
        return simulationRunning;
    }

    // Broadcast updates to all WebSocket sessions
    public void broadcastUpdate(String updateMessage) {
        synchronized (sessions) {
            sessions.removeIf(session -> {
                try {
                    if (session.isOpen()) {
                        session.sendMessage(new TextMessage(updateMessage));
                        return false; // Keep this session
                    }
                } catch (Exception e) {
                    System.err.println("Error sending message to session " + session.getId() + ": " + e.getMessage());
                }
                // Remove this session if it's closed or an error occurred
                return true;
            });
        }
    }

    // Method to add tickets to the pool
    public synchronized void addTicket(int ticketsToRelease, int maximumCapacity, int vendorId, int releaseInterval, Vendor vendor,int totaltickets,int ticketPrice,String eventName) {
        totalticket = totaltickets;
        for (int i = 0; i < ticketsToRelease; i++) {
            while (!simulationRunning) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (ticketList.size() <= maximumCapacity) {
                try {
                    Ticket ticket = new Ticket(ticketList.size()+1, eventName + vendorId, BigDecimal.valueOf(ticketPrice), vendor);
                    ticketList.add(ticket);
                    broadcastUpdate("Ticket added by Vendor " + vendorId + " with ticket ID: " + ticket.getId());
                    System.out.println("Vendor " + vendorId + " released ticket ID: " + ticket.getId() + " " );
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
            while (!simulationRunning) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
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
    public int getTicketCount() {
        return ticketList.size();
    }

    // Get the number of tickets bought by customers
    public int getCustomerBoughtTicketCount() {
        return customerBoughtTickets;
    }
    public int getTotalTicketCount(){
        return totalticket;
    }
}
