package vn.edu.hust.airplanecontext.domain.factory.seat.eventproduct;

import vn.edu.hust.airplanecontext.domain.message.event.seat.SeatRegisteredEvent;

public interface SeatRegisteredEventProduct {

    SeatRegisteredEvent createNewSeatRegisteredEvent();
}
