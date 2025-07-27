package vn.edu.hust.airplanecontext.domain.message.command.seat;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public record RegisterNewSeatCommand(
        @TargetAggregateIdentifier
        UUID seatId,
        String seatNumber,
        String seatClassId,
        String airplaneId
) {
}
