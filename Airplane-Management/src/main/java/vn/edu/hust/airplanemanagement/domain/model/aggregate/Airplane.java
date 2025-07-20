package vn.edu.hust.airplanemanagement.domain.model.aggregate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.command.AggregateVersion;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.hust.airplanemanagement.domain.message.command.airplane.RegisterNewAirplaneCommand;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.RegisterNewSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.event.airplane.AirplaneRegisteredEvent;
import vn.edu.hust.airplanemanagement.domain.model.entity.Seat;
import vn.edu.hust.airplanemanagement.domain.model.enumeration.AirplaneState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.Airline;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.AirplaneId;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.SeatId;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("All")
public class Airplane {

    @AggregateIdentifier
    private AirplaneId airplaneAggregateId;
    private String airplaneName;
    @AggregateMember
    Map<SeatId, Seat> seatMap = new HashMap<>();
    private Airline airline;
    private AirplaneState state;
    private int numberEmptySeat;
    @AggregateVersion
    private LocalDateTime version;

    @Autowired
    private CommandGateway commandGateway;

    @CommandHandler
    public Airplane(RegisterNewAirplaneCommand command) {
        command.seatDataList().parallelStream().forEach(seatData -> {
            var seat = commandGateway.sendAndWait(new RegisterNewSeatCommand(
                    seatData.seatNumber(),
                    seatData.seatClassId()
            ));
        });
//        AggregateLifecycle.apply(new AirplaneRegisteredEvent(
//                new AirplaneId(command.airplaneId()),
//                command.airplaneName(),
//
//        ))
    }

}
