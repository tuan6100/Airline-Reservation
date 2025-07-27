package vn.edu.hust.airplanecontext.domain.factory.airplane.eventproduct;

import vn.edu.hust.airplanecontext.domain.message.event.airplane.AirplaneTakenOffEvent;

public interface AirplaneTakenOffEventProduct {

    AirplaneTakenOffEvent createNewAirplaneTakenOffEvent();
}
