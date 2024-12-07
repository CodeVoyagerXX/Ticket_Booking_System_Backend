package com.ticket_booking.ticket_booking_system.Repository;

import com.ticket_booking.ticket_booking_system.Model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
}
