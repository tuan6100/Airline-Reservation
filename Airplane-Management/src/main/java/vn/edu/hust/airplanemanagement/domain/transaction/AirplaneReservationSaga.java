package vn.edu.hust.airplanemanagement.domain.transaction;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.HoldSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.event.airplane.NumberEmptySeatDecreasedEvent;

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
                (String) metaData.get("customer.id"),
                (String) metaData.get("passenger.id")
        ));
    }
}
