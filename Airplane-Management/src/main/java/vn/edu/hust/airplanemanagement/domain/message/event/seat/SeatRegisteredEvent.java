package vn.edu.hust.airplanemanagement.domain.message.event.seat;

import vn.edu.hust.airplanemanagement.domain.model.enumeration.SeatState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.SeatClass;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.SeatId;

public record SeatRegisteredEvent(
        SeatId seatId,
        String airplaneId,
        String seatNumber,
        SeatState state,
        SeatClass seatClass
) {
}
