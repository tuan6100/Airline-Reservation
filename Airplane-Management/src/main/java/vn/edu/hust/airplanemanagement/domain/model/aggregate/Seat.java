package vn.edu.hust.airplanemanagement.domain.model.aggregate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateVersion;
import org.axonframework.spring.stereotype.Aggregate;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.DeregisterSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.HoldSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.RegisterNewSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.event.seat.SeatRegisteredEvent;
import vn.edu.hust.airplanemanagement.domain.model.enumeration.SeatState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.SeatId;

import java.time.LocalDateTime;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("All")
public class Seat {

    @AggregateIdentifier
    private SeatId seatId;
    private String seatNumber;
    private SeatState state;
    private String seatClassId;
    private String airplaneId;
    private String heldByPassengerId;
    @AggregateVersion
    private LocalDateTime version;

    @CommandHandler
    public Seat(RegisterNewSeatCommand cmd) {
        AggregateLifecycle.apply(new SeatRegisteredEvent(
                new SeatId(cmd.seatId()),
                cmd.seatNumber(),
                SeatState.EMPTY,
                cmd.seatClassId(),
                cmd.airplaneId()
        ));
    }

    @EventSourcingHandler
    public void on(SeatRegisteredEvent event) {
        this.seatId = event.seatId();
        this.seatNumber = event.seatNumber();
        this.seatClassId = event.seatClassId();
        this.state = event.state();
    }

    @CommandHandler
    public void handle(DeregisterSeatCommand command) {
        AggregateLifecycle.markDeleted();
    }

}
