package vn.edu.hust.shared.payload.command.airplane;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

public record LandAirplaneCommand(
        @TargetAggregateIdentifier
        String airplaneId,
        LocalDateTime landedAt
) {
}
