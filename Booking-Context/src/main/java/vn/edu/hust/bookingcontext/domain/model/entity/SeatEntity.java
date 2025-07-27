package vn.edu.hust.bookingmanagement.domain.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.command.EntityId;
import vn.edu.hust.bookingmanagement.domain.model.enumeration.SeatState;

@Getter
@Setter
public class SeatEntity {

    @EntityId
    private Long seatId;
    private String seatNumber;
    private SeatState state;
    @AggregateMember
    private SeatClassEntity seatClassEntity;


}
