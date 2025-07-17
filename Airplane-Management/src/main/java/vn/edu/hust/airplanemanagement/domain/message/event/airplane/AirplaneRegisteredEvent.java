package vn.edu.hust.airplanemanagement.domain.message.event.airplane;

import vn.edu.hust.airplanemanagement.domain.model.entity.Seat;
import vn.edu.hust.airplanemanagement.domain.model.enumeration.AirplaneState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.Airline;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.SeatId;

import java.time.LocalDateTime;
import java.util.Map;

public record AirplaneRegisteredEvent(
        String airplaneId,
        String airplaneName,
        Map<SeatId, Seat> seatMap,
        Airline airline,
        AirplaneState state,
        int numberEmptySeat,
        LocalDateTime version
) {
}
