package vn.edu.hust.airplanecontext.domain.message.command.airplane;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public record RegisterNewAirplaneCommand(
        @TargetAggregateIdentifier
        UUID airplaneId,
        String airplaneName,

        String airlineId
) {
    public record SeatData(
       String seatNumber,
       String seatClassId
    ) {}
}
