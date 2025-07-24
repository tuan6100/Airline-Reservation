package vn.edu.hust.airplanemanagement.domain.model.aggregate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateVersion;
import org.axonframework.spring.stereotype.Aggregate;
import vn.edu.hust.airplanemanagement.domain.factory.FactoryGenerator;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.DeregisterSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.HoldSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.RegisterNewSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.event.seat.SeatRegisteredEvent;
import vn.edu.hust.airplanemanagement.domain.model.enumeration.SeatState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.Passenger;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.SeatClass;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.SeatId;

import java.time.LocalDateTime;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("All")
public class Seat {

    @AggregateIdentifier
    private SeatId seatId;
    private String airplaneId;
    private String seatNumber;
    private SeatState state;
    private SeatClass seatClassId;
    private Passenger heldByPassenger;
    @AggregateVersion
    private LocalDateTime version;

    @CommandHandler
    public Seat(RegisterNewSeatCommand cmd) {
        var factory = FactoryGenerator.getFactoryFromCommand(cmd);
        var event = factory.createNewSeatRegisteredEvent();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(SeatRegisteredEvent event) {
        this.seatId = event.seatId();
        this.seatNumber = event.seatNumber();
        this.seatClassId = event.seatClass();
        this.state = event.state();
    }

    @CommandHandler
    public void handle(DeregisterSeatCommand command) {
        AggregateLifecycle.markDeleted();
    }

    @CommandHandler
    public void handle(HoldSeatCommand command) {
        if (!this.state.equals(SeatState.EMPTY)) {
            throw new IllegalStateException("Seat has been already held " +
                    "by passenger" + this.heldByPassenger.getPassengerId());
        }


    }

}
