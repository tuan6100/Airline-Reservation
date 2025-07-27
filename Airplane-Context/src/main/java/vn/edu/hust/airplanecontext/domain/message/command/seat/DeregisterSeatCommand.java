package vn.edu.hust.airplanecontext.domain.message.command.seat;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record DeregisterSeatCommand(
        @TargetAggregateIdentifier
        String seatId
) {
}
