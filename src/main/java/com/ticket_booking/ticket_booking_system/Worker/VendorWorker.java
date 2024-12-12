package com.ticket_booking.ticket_booking_system.Worker;

import com.ticket_booking.ticket_booking_system.Model.Vendor;
import com.ticket_booking.ticket_booking_system.Service.TicketPool;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class VendorWorker implements Runnable {
    private final int vendorId;
    private final int ticketsToRelease;
    private final int releaseInterval;
    private final Vendor vendor;
    private final int maximumCapacity;
    private TicketPool ticketPool;
    private final int totalTicket;
    private final int ticketPrice;
    private final String eventName;

    @Override
    public void run() {
        ticketPool.addTicket(ticketsToRelease, maximumCapacity, vendorId, releaseInterval, vendor,totalTicket,ticketPrice,eventName);
    }

}
