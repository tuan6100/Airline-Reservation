package vn.edu.hust.airplanecontext.domain.factory.airplane.eventproduct;

import vn.edu.hust.airplanecontext.domain.message.event.airplane.AirplaneAssignedToFlightEvent;

public interface AirplaneAssignedToFlightProduct {

    AirplaneAssignedToFlightEvent createNewAirplaneAssignedToFlightEvent();
}
