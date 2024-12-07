package com.ticket_booking.ticket_booking_system.Service;

import com.ticket_booking.ticket_booking_system.Model.Ticket;
import com.ticket_booking.ticket_booking_system.Repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;


    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
