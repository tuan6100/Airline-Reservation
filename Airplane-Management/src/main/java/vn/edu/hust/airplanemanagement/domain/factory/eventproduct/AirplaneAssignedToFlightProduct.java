package vn.edu.hust.airplanemanagement.domain.factory.eventproduct;

import vn.edu.hust.airplanemanagement.domain.message.event.airplane.AirplaneAssignedToFlightEvent;

public interface AirplaneAssignedToFlightProduct {

    AirplaneAssignedToFlightEvent raiseNewAirplaneAssignedToFlightEvent();
}
