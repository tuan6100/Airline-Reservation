package vn.edu.hust.airplanemanagement.domain.message.event.seat;

import vn.edu.hust.airplanemanagement.domain.model.enumeration.SeatState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.SeatId;

public record SeatHeldEvent(
        SeatId seatId,
        SeatState state,
        String passengerId
) {
}
