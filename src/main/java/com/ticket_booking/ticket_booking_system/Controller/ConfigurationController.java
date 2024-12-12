package com.ticket_booking.ticket_booking_system.Controller;

import com.ticket_booking.ticket_booking_system.Model.Configuration;
import com.ticket_booking.ticket_booking_system.Model.Vendor;
import com.ticket_booking.ticket_booking_system.Repository.TicketRepository;
import com.ticket_booking.ticket_booking_system.Repository.VendorRepository;
import com.ticket_booking.ticket_booking_system.Service.TicketPool;
import com.ticket_booking.ticket_booking_system.Worker.CustomerWorker;
import com.ticket_booking.ticket_booking_system.Worker.VendorWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")  // Allow frontend from localhost:3000
@RequiredArgsConstructor
public class ConfigurationController {

    private Vendor vendor;
    @Autowired
    private TicketPool ticketPool ;


    private Configuration configuration = new Configuration(); // Temporary storage, can be replaced with DB

    @PostMapping("/api/configuration")
    public String saveConfiguration(@RequestBody Configuration config) {
        this.configuration = config;


        return "Configuration saved!";
    }
    @PostMapping("/api/stop-simulation")
    public String stopSimulation() {
        ticketPool.setSimulationRunning(false);
        synchronized (ticketPool) {
            ticketPool.notifyAll(); // Wake up all threads to check the flag
        }
        return "Simulation stopped!";
    }
    @PostMapping("/api/start-simulation")
    public String startSimulation() {


        if (configuration == null) {
            return "Configuration not set. Please configure first.";
        } else{
                ticketPool.setSimulationRunning(true);
                synchronized (ticketPool) {
                    ticketPool.notifyAll();
            }
        }
        return simulateConcurrency(configuration);
    }


    private String simulateConcurrency(Configuration config) {
        int totalTickets = config.getTotalTickets();
        int customers = config.getNumberOfCustomers(); // Example: Customer threads
        int ticketsPerCustomer = 5;
        int vendors = config.getNumberOfVendors(); // Example: Vendor threads
        int ticketsPerVendor = totalTickets / vendors;
        int releaseInterval = config.getTicketReleaseRate();
        int customerRetrievalRate = config.getCustomerRetrievalRate();
        int MaximumCapacity = config.getMaxTicketCapacity();
        int ticketPrice = config.getTicketPrice();
        String eventName = config.getEventName();


        // Create and start Vendor threads
        for (int i = 1; i <= vendors; i++) {
            VendorWorker vendorWorker = new VendorWorker(i, ticketsPerVendor, releaseInterval,  vendor, MaximumCapacity,ticketPool,totalTickets,ticketPrice,eventName);
            Thread vendorThread = new Thread(vendorWorker);
            vendorThread.start();
        }

        // Create and start Customer threads
        for (int i = 1; i <= customers; i++) {
            CustomerWorker customerWorker = new CustomerWorker(i, ticketsPerCustomer, customerRetrievalRate, ticketPool);
            Thread customerThread = new Thread(customerWorker);
            customerThread.start();



        }
            return "Simulation started with the provided configuration! Check logs for details.";

    }


}