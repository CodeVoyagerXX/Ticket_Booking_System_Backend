package com.ticket_booking.ticket_booking_system.WebSocket;

import com.ticket_booking.ticket_booking_system.Service.TicketPool;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class TicketWebSocketHandler extends TextWebSocketHandler {

    private final TicketPool ticketPool;

    // Constructor that accepts the TicketPool to broadcast updates
    public TicketWebSocketHandler(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        // Register the WebSocket session when a new connection is established
        ticketPool.registerSession(session);
        System.out.println("New WebSocket session connected: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming messages (optional, depending on your requirements)
        super.handleTextMessage(session, message);
        System.out.println("Received message: " + message.getPayload());

        // Example: Send a response back to the client if needed
        session.sendMessage(new TextMessage("Message received: " + message.getPayload()));
    }

    // Method to send updates to all WebSocket clients via the TicketPool
    public void sendUpdateToAllClients(String updateMessage) {
        // Broadcasting the update to all connected WebSocket clients
        ticketPool.broadcastUpdate(updateMessage);
    }

    // Method to send a direct update to a specific WebSocket session
    public void sendUpdate(WebSocketSession session, String updateMessage) throws Exception {
        session.sendMessage(new TextMessage(updateMessage));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println("WebSocket session closed: " + session.getId());
    }
}
