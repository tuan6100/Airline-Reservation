package vn.edu.hust.airplanecontext.domain.message.event.seat;

import vn.edu.hust.airplanecontext.domain.model.enumeration.SeatState;
import vn.edu.hust.airplanecontext.domain.model.valueobj.SeatClass;
import vn.edu.hust.common.domain.id.SeatId;

public record SeatRegisteredEvent(
        SeatId seatId,
        String airplaneId,
        String seatNumber,
        SeatState state,
        SeatClass seatClass
) {
}
