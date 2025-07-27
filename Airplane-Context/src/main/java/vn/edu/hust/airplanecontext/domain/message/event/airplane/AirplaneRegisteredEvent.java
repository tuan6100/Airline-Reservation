package vn.edu.hust.airplanecontext.domain.message.event.airplane;

import vn.edu.hust.airplanecontext.domain.model.enumeration.AirplaneState;
import vn.edu.hust.airplanecontext.domain.model.valueobj.Airline;
import vn.edu.hust.common.domain.id.AirplaneId;

public record AirplaneRegisteredEvent(
        AirplaneId airplaneId,
        String airplaneName,
        Airline airline,
        AirplaneState state
) {
}
