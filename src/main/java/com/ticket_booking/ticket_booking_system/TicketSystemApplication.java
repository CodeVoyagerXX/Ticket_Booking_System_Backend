package com.ticket_booking.ticket_booking_system;

import com.ticket_booking.ticket_booking_system.Model.Customer;
import com.ticket_booking.ticket_booking_system.Model.Vendor;
import com.ticket_booking.ticket_booking_system.Service.CustomerService;
import com.ticket_booking.ticket_booking_system.Service.VendorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(VendorService vendorService, CustomerService customerService) {
		return args -> {
			Vendor vendor1 = new Vendor(0, "Vendor1", null);
			Vendor vendor2 = new Vendor(0, "Vendor2", null);

			vendorService.addVendor(vendor1);
			vendorService.addVendor(vendor2);

			Customer customer1 = new Customer(0, "Customer1", 0);
			Customer customer2 = new Customer(0, "Customer2", 0);

			customerService.addCustomer(customer1);
			customerService.addCustomer(customer2);
		};
	}
}

