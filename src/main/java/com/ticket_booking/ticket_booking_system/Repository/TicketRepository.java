package com.ticket_booking.ticket_booking_system.Repository;

import com.ticket_booking.ticket_booking_system.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findTopByOrderByIdAsc();
}
