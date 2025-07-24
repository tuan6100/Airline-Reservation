package vn.edu.hust.airplanemanagement.domain.model.aggregate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import vn.edu.hust.airplanemanagement.domain.factory.FactoryGenerator;
import vn.edu.hust.airplanemanagement.domain.message.command.airplane.AddSeatToAirplaneCommand;
import vn.edu.hust.airplanemanagement.domain.message.command.airplane.AssignAirplaneToFlightCommand;
import vn.edu.hust.airplanemanagement.domain.message.command.airplane.MakeAReservationCommand;
import vn.edu.hust.airplanemanagement.domain.message.command.airplane.RegisterNewAirplaneCommand;
import vn.edu.hust.airplanemanagement.domain.message.event.airplane.AirplaneAssignedToFlightEvent;
import vn.edu.hust.airplanemanagement.domain.message.event.airplane.AirplaneRegisteredEvent;
import vn.edu.hust.airplanemanagement.domain.message.event.airplane.NumberEmptySeatDecreasedEvent;
import vn.edu.hust.airplanemanagement.domain.message.event.airplane.SeatAddedToAirplaneEvent;
import vn.edu.hust.airplanemanagement.domain.model.entity.Attendant;
import vn.edu.hust.airplanemanagement.domain.model.entity.Pilot;
import vn.edu.hust.airplanemanagement.domain.model.enumeration.AirplaneState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.Airline;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.Flight;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.AirplaneId;

import java.util.Map;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("All")
public class Airplane {

    @AggregateIdentifier
    private AirplaneId airplaneAggregateId;
    private String airplaneName;
    private Airline airline;
    private AirplaneState state;
    private Flight flight;
    private int numberEmptySeat = 0;
    @AggregateMember
    private Pilot primaryPilot;
    @AggregateMember
    private Pilot secondaryPilot;
    @AggregateMember
    private Map<String, Attendant> attendants;

    @CommandHandler
    public Airplane(RegisterNewAirplaneCommand command) {
        var factory = FactoryGenerator.getFactoryFromCommand(command);
        var event = factory.createNewAirplaneRegisteredEvent();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AirplaneRegisteredEvent event) {
        this.airplaneAggregateId = event.airplaneId();
        this.airplaneName = event.airplaneName();
        this.airline = airline;
        this.state = event.state();
    }

    @CommandHandler
    public void handle(AddSeatToAirplaneCommand command) {
        AggregateLifecycle.apply(new SeatAddedToAirplaneEvent(
                command.airplaneId(),
                this.numberEmptySeat++
        ));
    }

    @EventSourcingHandler
    public void on(SeatAddedToAirplaneEvent event) {
        this.numberEmptySeat = event.numberEmptySeat();
    }

    @CommandHandler
    public void handle(AssignAirplaneToFlightCommand command) {
        var factory = FactoryGenerator.getFactoryFromCommand(command);
        var event = factory.createNewAirplaneAssignedToFlightEvent();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AirplaneAssignedToFlightEvent event) {

    }

    @CommandHandler
    public void handle(MakeAReservationCommand command) {
        if (!this.state.equals(AirplaneState.SCHEDULED)) {
            throw new IllegalStateException(
                    "Airplane" + this.airplaneAggregateId + " is not available for reserving");
        }
        AggregateLifecycle.apply(new NumberEmptySeatDecreasedEvent(
                command.airplaneId(),
                command.seatId(),
                this.numberEmptySeat - 1
        ), MetaData.from(Map.of(
                "customer.id", command.customerId(),
                "passenger.id", command.passengerId()
        )));
    }

    @EventSourcingHandler
    void on(NumberEmptySeatDecreasedEvent event) {
        this.numberEmptySeat = numberEmptySeat;
    }



}
