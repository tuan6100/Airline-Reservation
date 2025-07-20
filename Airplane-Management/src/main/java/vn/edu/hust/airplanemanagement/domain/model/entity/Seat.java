package vn.edu.hust.airplanemanagement.domain.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.EntityId;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.RegisterNewSeatCommand;
import vn.edu.hust.airplanemanagement.domain.model.enumeration.SeatState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.Passenger;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.SeatClass;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.SeatId;

@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor
public class Seat {

    @EntityId
    private SeatId seatId;
    private String seatNumber;
    private SeatClass seatClass;
    private SeatState state;
    private Passenger passenger;

    @CommandHandler
    public Seat(RegisterNewSeatCommand command) {

    }
}
