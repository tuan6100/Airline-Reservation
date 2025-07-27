package vn.edu.hust.airplanecontext.domain.message.command.airplane;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record MakeAReservationCommand (
        @TargetAggregateIdentifier
        String airplaneId,
        String seatId,
        String passengerId,
        String customerId,
        String flightId
) {

}
