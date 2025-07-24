package vn.edu.hust.airplanemanagement.domain.factory.seat.eventproduct;

import vn.edu.hust.airplanemanagement.domain.message.event.seat.SeatRegisteredEvent;

public interface SeatRegisteredEventProduct {

    SeatRegisteredEvent createNewSeatRegisteredEvent();
}
