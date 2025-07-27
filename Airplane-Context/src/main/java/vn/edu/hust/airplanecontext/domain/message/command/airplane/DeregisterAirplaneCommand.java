package vn.edu.hust.airplanecontext.domain.message.command.airplane;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record DeregisterAirplaneCommand(
        @TargetAggregateIdentifier
        String airplaneId
) {
}
