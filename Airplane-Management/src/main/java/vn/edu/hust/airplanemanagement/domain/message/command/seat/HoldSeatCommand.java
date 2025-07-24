package vn.edu.hust.airplanemanagement.domain.message.command.seat;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public record HoldSeatCommand(
        @TargetAggregateIdentifier
        String seatId,
        String customerId,
        String passengerId
) {
}
