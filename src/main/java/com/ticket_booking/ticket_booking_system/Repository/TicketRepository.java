package com.ticket_booking.ticket_booking_system.Repository;

import com.ticket_booking.ticket_booking_system.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
