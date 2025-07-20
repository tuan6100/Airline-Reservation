package vn.edu.hust.bookingmanagement.domain.message.query;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record GetSeatClassQuery(
        @TargetAggregateIdentifier
        Long seatId
) {
}
