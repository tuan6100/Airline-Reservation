package vn.edu.hust.airplanecontext.domain.message.command.seat;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record HoldSeatCommand(
        @TargetAggregateIdentifier
        String seatId,
        String airplaneId,
        String customerId,
        String passengerId
) {
}
