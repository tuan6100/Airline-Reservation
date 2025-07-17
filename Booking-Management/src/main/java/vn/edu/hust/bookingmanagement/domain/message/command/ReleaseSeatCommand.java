package vn.edu.hust.bookingmanagement.domain.message.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record ReleaseSeatCommand(
        @TargetAggregateIdentifier
        String customerAggregateId,
        Long seatId,
        String reason
) {
}
