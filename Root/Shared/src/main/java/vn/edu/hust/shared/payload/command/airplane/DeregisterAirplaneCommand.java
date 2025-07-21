package vn.edu.hust.shared.payload.command.airplane;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record DeregisterAirplaneCommand(
        @TargetAggregateIdentifier
        String airplaneId
) {
}
