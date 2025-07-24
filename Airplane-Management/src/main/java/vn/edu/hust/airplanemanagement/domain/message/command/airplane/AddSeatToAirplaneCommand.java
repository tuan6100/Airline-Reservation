package vn.edu.hust.airplanemanagement.domain.message.command.airplane;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AddSeatToAirplaneCommand(
        @TargetAggregateIdentifier
        String airplaneId
) {
}
