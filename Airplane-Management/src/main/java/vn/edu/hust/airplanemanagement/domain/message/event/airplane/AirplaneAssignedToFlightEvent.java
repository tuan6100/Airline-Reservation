package vn.edu.hust.airplanemanagement.domain.message.event.airplane;

import vn.edu.hust.airplanemanagement.domain.model.entity.Attendant;
import vn.edu.hust.airplanemanagement.domain.model.entity.Pilot;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.Flight;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.AirplaneId;

import java.util.Map;

public record AirplaneAssignedToFlightEvent (
        AirplaneId airplaneId,
        Flight flight,
        Pilot primaryPilot,
        Pilot secondaryPilot,
        Map<String, Attendant> attendants
) {
}
