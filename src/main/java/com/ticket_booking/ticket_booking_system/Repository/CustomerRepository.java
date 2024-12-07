package com.ticket_booking.ticket_booking_system.Repository;

import com.ticket_booking.ticket_booking_system.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

