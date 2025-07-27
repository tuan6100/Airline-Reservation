package vn.edu.hust.airplanecontext.domain.factory.airplane.eventproduct;

import vn.edu.hust.airplanecontext.domain.message.event.airplane.AirplaneRegisteredEvent;

public interface AirplaneRegisteredEventProduct {

    AirplaneRegisteredEvent createNewAirplaneRegisteredEvent();
}
