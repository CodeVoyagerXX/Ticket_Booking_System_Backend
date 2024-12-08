package com.ticket_booking.ticket_booking_system.Worker;

import com.ticket_booking.ticket_booking_system.Service.TicketPool;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CustomerWorker implements Runnable {
    private final int customerId;
    private final int ticketsToBuy;
    private final int retrievalInterval;
    private TicketPool ticketPool;


    @Override
    public void run() {
        ticketPool.buyTicket( ticketsToBuy, customerId, retrievalInterval);
    }



    }


