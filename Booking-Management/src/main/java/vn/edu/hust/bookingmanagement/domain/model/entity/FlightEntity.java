package vn.edu.hust.bookingmanagement.domain.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.command.EntityId;
import vn.edu.hust.bookingmanagement.domain.model.valueobj.FlightDetail;

import java.util.Map;


@Getter
@Setter
public class FlightEntity {

    @EntityId
    private Long flightId;
    @AggregateMember(routingKey = "airplaneId")
    private Map<Long,AirplaneEntity> airplanes;
    

}
