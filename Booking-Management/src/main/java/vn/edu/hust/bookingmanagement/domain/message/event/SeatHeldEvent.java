package vn.edu.hust.bookingmanagement.domain.message.event;

import vn.edu.hust.bookingmanagement.domain.model.enumeration.SeatState;


public record SeatHeldEvent(
    String aggregateId,
    Long seatId,
    Long customerId,
    SeatState state
){
}
