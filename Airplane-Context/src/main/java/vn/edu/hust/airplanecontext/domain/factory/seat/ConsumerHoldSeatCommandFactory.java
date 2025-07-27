package vn.edu.hust.airplanecontext.domain.factory.seat;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.airplanecontext.domain.factory.seat.eventproduct.SeatHeldEventProduct;
import vn.edu.hust.airplanecontext.domain.message.command.seat.HoldSeatCommand;
import vn.edu.hust.airplanecontext.domain.message.event.seat.SeatHeldEvent;
import vn.edu.hust.airplanecontext.domain.message.query.GetPassengerQuery;
import vn.edu.hust.airplanecontext.domain.model.enumeration.SeatState;
import vn.edu.hust.airplanecontext.domain.model.valueobj.Passenger;
import vn.edu.hust.common.domain.id.SeatId;

import java.util.concurrent.ExecutionException;

@Component
public class ConsumerHoldSeatCommandFactory
implements SeatHeldEventProduct {

    @Autowired
    private QueryGateway queryGateway;

    private final HoldSeatCommand command;

    public ConsumerHoldSeatCommandFactory() {
        this.command = null;
    }

    public ConsumerHoldSeatCommandFactory(HoldSeatCommand command) {
        this.command = command;
    }

    @Override
    public SeatHeldEvent createNewSeatHeldEvent() {
        if (command == null) {
            throw new RuntimeException("Command should not be null");
        }
        var getPassengerQuery = new GetPassengerQuery(command.passengerId());
        try {
            var passenger = queryGateway.query(getPassengerQuery, Passenger.class)
                    .get();
            return new SeatHeldEvent(
                    new SeatId(command.seatId()),
                    command.airplaneId(),
                    SeatState.ON_HOLD,
                    passenger
            );
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve seat class", e);
        }
    }
}
