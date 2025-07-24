package vn.edu.hust.airplanemanagement.domain.factory.airplane.eventproduct;

import vn.edu.hust.airplanemanagement.domain.message.event.airplane.AirplaneTakenOffEvent;

public interface AirplaneTakenOffEventProduct {

    AirplaneTakenOffEvent createNewAirplaneTakenOffEvent();
}
