package vn.edu.hust.bookingmanagement.domain.message.command.ticket;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public record GenerateTicketCommand(
        @TargetAggregateIdentifier
        UUID ticketId,
        String passengerId,
        String seatId,
        String airplaneId,
        String flightId

) {
}
