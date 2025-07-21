package vn.edu.hust.airplanemanagement.domain.factory;

import vn.edu.hust.airplanemanagement.domain.factory.eventproduct.AirplaneAssignedToFlightProduct;
import vn.edu.hust.airplanemanagement.domain.message.command.airplane.AssignAirplaneToFlightCommand;
import vn.edu.hust.airplanemanagement.domain.message.event.airplane.AirplaneAssignedToFlightEvent;
import vn.edu.hust.airplanemanagement.domain.model.entity.Attendant;
import vn.edu.hust.airplanemanagement.domain.model.entity.Pilot;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.Flight;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.AirplaneId;

import java.util.stream.Collectors;

public class ConsumerAssignAirplaneToFlightCommandFactory
implements AirplaneAssignedToFlightProduct {

    private final AssignAirplaneToFlightCommand command;

    public ConsumerAssignAirplaneToFlightCommandFactory(AssignAirplaneToFlightCommand command) {
        this.command = command;
    }

    @Override
    public AirplaneAssignedToFlightEvent raiseNewAirplaneAssignedToFlightEvent() {
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
                airplane,
                flight,
                primaryPilot,
                secondaryPilot,
                attendants
        );
    }
}
