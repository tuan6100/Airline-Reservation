package vn.edu.hust.shared.payload.command.airplane;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Set;
import java.util.UUID;

public record AssignAirplaneToFlightCommand(
        @TargetAggregateIdentifier
        UUID airplaneId,
        String flightId,
        String primaryPilotId,
        String secondaryPilotId,
        Set<String> attendantIdSet
) {
}
