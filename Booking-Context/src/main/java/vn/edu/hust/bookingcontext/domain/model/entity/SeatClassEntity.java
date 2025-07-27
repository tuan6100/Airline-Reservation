package vn.edu.hust.bookingmanagement.domain.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.EntityId;

@Getter
@Setter
public class SeatClassEntity {

    @EntityId
    private Long seatClassId;
    private String seatClassName;
    private long price;
}
