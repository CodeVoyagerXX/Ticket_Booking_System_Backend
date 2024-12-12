package com.ticket_booking.ticket_booking_system.WebSocket;

import com.ticket_booking.ticket_booking_system.Service.TicketPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final TicketPool ticketPool;

    // Constructor injection of TicketPool to provide it to WebSocketHandler
    public WebSocketConfig(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Register the TicketWebSocketHandler and pass the TicketPool to it
        registry.addHandler(new TicketWebSocketHandler(ticketPool), "/ws/tickets")
                .setAllowedOrigins("http://localhost:3000"); // Allow frontend from localhost:3000
    }
}
