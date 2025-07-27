package vn.edu.hust.airplanecontext.domain.message.event.seat;

import vn.edu.hust.airplanecontext.domain.model.enumeration.SeatState;
import vn.edu.hust.airplanecontext.domain.model.valueobj.Passenger;
import vn.edu.hust.common.domain.id.SeatId;

public record SeatHeldEvent(
        SeatId seatId,
        String airplaneId,
        SeatState state,
        Passenger passenger
) {
}
