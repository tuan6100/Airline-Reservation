package vn.edu.hust.airplanemanagement.domain.factory.seat;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.airplanemanagement.domain.factory.seat.eventproduct.SeatRegisteredEventProduct;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.RegisterNewSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.event.seat.SeatRegisteredEvent;
import vn.edu.hust.airplanemanagement.domain.message.query.GetSeatClassQuery;
import vn.edu.hust.airplanemanagement.domain.model.enumeration.SeatState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.SeatClass;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.SeatId;

import java.util.concurrent.ExecutionException;

@Component
public class ConsumerRegisterNewSeatCommand
implements SeatRegisteredEventProduct {

    @Autowired
    private QueryGateway queryGateway;

    private final RegisterNewSeatCommand command;

    public ConsumerRegisterNewSeatCommand() {
        this.command = null;
    }

    public ConsumerRegisterNewSeatCommand(RegisterNewSeatCommand command) {
        this.command = command;
    }

    @Override
    public SeatRegisteredEvent createNewSeatRegisteredEvent() {
        if (command == null) {
            throw new RuntimeException("Command should not be null");
        }
        var getSeatClassQuery = new GetSeatClassQuery(command.seatClassId());
        try {
            var seatClass = queryGateway.query(getSeatClassQuery, SeatClass.class)
                    .get();
            return new SeatRegisteredEvent(
                    new SeatId(command.seatId()),
                    command.airplaneId(),
                    command.seatNumber(),
                    SeatState.EMPTY,
                    seatClass
            );
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve seat class", e);
        }
    }
}
