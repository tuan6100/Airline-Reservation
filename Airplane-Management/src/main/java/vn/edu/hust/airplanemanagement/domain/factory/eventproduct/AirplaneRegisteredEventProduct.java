package vn.edu.hust.airplanemanagement.domain.factory.eventproduct;

import vn.edu.hust.airplanemanagement.domain.message.event.airplane.AirplaneRegisteredEvent;

public interface AirplaneRegisteredEventProduct {

    AirplaneRegisteredEvent raiseNewAirplaneRegisteredEvent();
}
