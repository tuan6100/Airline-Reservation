package vn.edu.hust.bookingmanagement.domain.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.command.EntityId;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class AirplaneEntity {

    @EntityId
    private Long airplaneId;

    @AggregateMember(routingKey = "seatId")
    private Map<Long, SeatEntity> seats = new HashMap<>();

}
