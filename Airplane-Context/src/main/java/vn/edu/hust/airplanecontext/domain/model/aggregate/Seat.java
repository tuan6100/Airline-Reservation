package vn.edu.hust.airplanecontext.domain.model.aggregate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import vn.edu.hust.airplanecontext.domain.factory.FactoryGenerator;
import vn.edu.hust.airplanecontext.domain.message.command.seat.DeregisterSeatCommand;
import vn.edu.hust.airplanecontext.domain.message.command.seat.HoldSeatCommand;
import vn.edu.hust.airplanecontext.domain.message.command.seat.RegisterNewSeatCommand;
import vn.edu.hust.airplanecontext.domain.message.event.seat.SeatHeldEvent;
import vn.edu.hust.airplanecontext.domain.message.event.seat.SeatRegisteredEvent;
import vn.edu.hust.airplanecontext.domain.model.enumeration.SeatState;
import vn.edu.hust.airplanecontext.domain.model.valueobj.Passenger;
import vn.edu.hust.airplanecontext.domain.model.valueobj.SeatClass;
import vn.edu.hust.common.domain.id.SeatId;

import java.time.LocalDateTime;
import java.util.Map;

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
    public void handle(HoldSeatCommand command, MetaData metaData) {
        if (!this.state.equals(SeatState.EMPTY)) {
            throw new IllegalStateException("Seat has been already held " +
                    "by passenger" + this.heldByPassenger.getPassengerId());
        }
        var factory = FactoryGenerator.getFactoryFromCommand(command);
        var event = factory.createNewSeatHeldEvent();
        AggregateLifecycle.apply(event, MetaData.from(Map.of(
                "flight.id", metaData.get("flight.id"),
                "customer.id", metaData.get("customer.id")
        )));
    }

    @EventSourcingHandler
    public void on(SeatHeldEvent event) {
        this.state = event.state();
        this.heldByPassenger = event.passenger();
    }

}
