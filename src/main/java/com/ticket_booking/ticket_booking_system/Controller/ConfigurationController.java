package com.ticket_booking.ticket_booking_system.Controller;

import com.ticket_booking.ticket_booking_system.Model.Configuration;
import com.ticket_booking.ticket_booking_system.Task.CustomerTask;
import com.ticket_booking.ticket_booking_system.Task.VendorTask;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuration")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")  // Allow frontend from localhost:3000
public class ConfigurationController {

    private final VendorTask vendorTask;
    private final CustomerTask customerTask;
    private Configuration configuration = new Configuration(); // Temporary storage, can be replaced with DB

    @PostMapping
    public String saveConfiguration(@RequestBody Configuration config) {
        this.configuration = config;

        // Trigger the simulation
        return simulateConcurrency();
    }

    private String simulateConcurrency() {
        // Vendor 1 releasing tickets
        vendorTask.releaseTickets("Vendor1", configuration.getTotalTickets());

        // Vendor 2 releasing tickets
        vendorTask.releaseTickets("Vendor2", configuration.getTotalTickets());

        // Customer 1 retrieving tickets
        customerTask.retrieveTickets("Customer1", 5);

        // Customer 2 retrieving tickets
        customerTask.retrieveTickets("Customer2", 5);

        return "Configuration updated successfully, and simulation started. Check logs for details.";
    }
}
