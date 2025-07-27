package vn.edu.hust.airplanecontext.domain.transaction;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.hust.airplanecontext.domain.message.command.seat.HoldSeatCommand;
import vn.edu.hust.airplanecontext.domain.message.event.airplane.NumberEmptySeatDecreasedEvent;
import vn.edu.hust.airplanecontext.domain.message.event.seat.SeatHeldEvent;
import vn.edu.hust.common.domain.util.UUIDGenerator;
import vn.edu.hust.bookingmanagement.domain.message.command.ticket.GenerateTicketCommand;

import java.util.Map;

@Slf4j
@Saga
public class AirplaneReservationSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "airplaneId")
    public void handle(NumberEmptySeatDecreasedEvent event, MetaData metaData) {
        SagaLifecycle.associateWith("seat", event.seatId());
        commandGateway.sendAndWait(new HoldSeatCommand(
                event.seatId(),
                event.airplaneId(),
                (String) metaData.get("customer.id"),
                (String) metaData.get("passenger.id")
        ), MetaData.with("flight.id", metaData.get("flight.id")));
    }

    @SagaEventHandler(associationProperty = "seatId")
    public void handle(SeatHeldEvent event, MetaData metaData) {
        var ticketId = UUIDGenerator.generate(7, null);
        SagaLifecycle.associateWith("ticket", ticketId.toString());
        commandGateway.sendAndWait(new GenerateTicketCommand(
                ticketId,
                event.passenger().getPassengerId(),
                event.seatId().toString(),
                event.airplaneId(),
                (String) metaData.get("flight.id")
        ), MetaData.from(Map.of(
                "customer.id", metaData.get("customer.id")
        )));
    }
}
