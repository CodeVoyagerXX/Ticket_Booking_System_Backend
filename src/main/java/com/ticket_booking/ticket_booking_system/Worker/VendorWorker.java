package com.ticket_booking.ticket_booking_system.Worker;

import com.ticket_booking.ticket_booking_system.Model.Vendor;
import com.ticket_booking.ticket_booking_system.Repository.TicketRepository;
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

    @Override
    public void run() {
        ticketPool.addTicket(ticketsToRelease, maximumCapacity, vendorId, releaseInterval, vendor);
    }

}
