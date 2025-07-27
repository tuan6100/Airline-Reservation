package vn.edu.hust.airplanecontext.domain.message.event.airplane;

import vn.edu.hust.airplanecontext.domain.model.entity.Attendant;
import vn.edu.hust.airplanecontext.domain.model.entity.Pilot;
import vn.edu.hust.airplanecontext.domain.model.valueobj.Flight;

import java.util.Map;

public record AirplaneAssignedToFlightEvent (
        Flight flight,
        Pilot primaryPilot,
        Pilot secondaryPilot,
        Map<String, Attendant> attendants
) {
}
