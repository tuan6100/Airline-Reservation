package vn.edu.hust.airplanemanagement.domain.message.event.airplane;

import vn.edu.hust.airplanemanagement.domain.model.enumeration.AirplaneState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.Airline;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.AirplaneId;

public record AirplaneRegisteredEvent(
        AirplaneId airplaneId,
        String airplaneName,
        Airline airline,
        AirplaneState state
) {
}
