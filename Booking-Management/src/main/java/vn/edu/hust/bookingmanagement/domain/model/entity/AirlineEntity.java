package vn.edu.hust.bookingmanagement.domain.model.entity;

import org.axonframework.modelling.command.EntityId;

public class AirlineEntity {

    @EntityId
    private Long airlineId;
    private int maxBookingAllowedPerUserPerAirplane;
    private CharSequence timeLimitForSeatHolding;

}
