/*
package com.ticket_booking.ticket_booking_system.Controller;

import com.ticket_booking.ticket_booking_system.Task.CustomerTask;
import com.ticket_booking.ticket_booking_system.Task.VendorTask;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ticket_booking.ticket_booking_system.Model.Configuration;


@RestController
@RequiredArgsConstructor
public class ConcurrencyController {

    private final VendorTask vendorTask;
    private final CustomerTask customerTask;
    private final Configuration configuration;

    @GetMapping("/simulate")
    public String simulateConcurrency() {
        // Vendor 1 releasing tickets
        vendorTask.releaseTickets("Vendor1", configuration.getTotalTickets());

        // Vendor 2 releasing tickets
        vendorTask.releaseTickets("Vendor2", configuration.getTotalTickets());

        // Customer 1 retrieving tickets
        customerTask.retrieveTickets("Customer1", 5);

        // Customer 2 retrieving tickets
        customerTask.retrieveTickets("Customer2", 5);

        return "Simulation started. Check the logs for activity.";
    }
}
*/
