package vn.edu.hust.airplanecontext.domain.factory.airplane;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.airplanecontext.domain.factory.airplane.eventproduct.AirplaneAssignedToFlightProduct;
import vn.edu.hust.airplanecontext.domain.message.command.airplane.AssignAirplaneToFlightCommand;
import vn.edu.hust.airplanecontext.domain.message.event.airplane.AirplaneAssignedToFlightEvent;
import vn.edu.hust.airplanecontext.domain.model.entity.Attendant;
import vn.edu.hust.airplanecontext.domain.model.entity.Pilot;
import vn.edu.hust.airplanecontext.domain.model.valueobj.Flight;
import vn.edu.hust.common.domain.id.AirplaneId;

import java.util.stream.Collectors;

@Component
public class ConsumerAssignAirplaneToFlightCommandFactory
implements AirplaneAssignedToFlightProduct {

    @Autowired
    private QueryGateway queryGateway;

    private final AssignAirplaneToFlightCommand command;

    public ConsumerAssignAirplaneToFlightCommandFactory() {
        this.command = null;
    }

    public ConsumerAssignAirplaneToFlightCommandFactory(AssignAirplaneToFlightCommand command) {
        this.command = command;
    }

    @Override
    public AirplaneAssignedToFlightEvent createNewAirplaneAssignedToFlightEvent() {
        if (command == null) {
            throw new RuntimeException("Command should not be null");
        }
        // Domain rules are here
        var airplane =  new AirplaneId(command.airplaneId());
        var flight = new Flight(command.flightId());
        var primaryPilot = new Pilot(
                command.primaryPilotId(),
                true,
                null);
        var secondaryPilot = new Pilot(
                command.secondaryPilotId(),
                true,
                null
        );
        var attendants = command.attendantIdSet().stream()
                .map(attendantId -> new Attendant(
                        attendantId,
                        true,
                        null))
                .collect(Collectors.toMap(
                        Attendant::getAttendantId,
                        attendant -> attendant
                ));
        return new AirplaneAssignedToFlightEvent(
                flight,
                primaryPilot,
                secondaryPilot,
                attendants
        );
    }
}
