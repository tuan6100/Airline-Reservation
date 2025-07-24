package vn.edu.hust.airplanemanagement.domain.factory.airplane.eventproduct;

import vn.edu.hust.airplanemanagement.domain.message.event.airplane.AirplaneAssignedToFlightEvent;

public interface AirplaneAssignedToFlightProduct {

    AirplaneAssignedToFlightEvent createNewAirplaneAssignedToFlightEvent();
}
