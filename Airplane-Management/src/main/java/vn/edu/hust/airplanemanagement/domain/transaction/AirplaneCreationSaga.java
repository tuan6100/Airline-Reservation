package vn.edu.hust.airplanemanagement.domain.transaction;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.distributed.CommandDispatchException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.modelling.saga.repository.LockingSagaRepository;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import vn.edu.hust.airplanemanagement.domain.message.command.airplane.AddSeatToAirplaneCommand;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.DeregisterSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.event.airplane.AirplaneRegisteredEvent;
import vn.edu.hust.airplanemanagement.domain.message.event.airplane.SeatAddedToAirplaneEvent;
import vn.edu.hust.airplanemanagement.domain.message.event.seat.SeatRegisteredEvent;

import java.util.concurrent.TimeUnit;

@Slf4j
@Saga
public class AirplaneCreationSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @CircuitBreaker(
            retryFor = CommandDispatchException.class,
            maxAttempts = 5,
            openTimeout = 3000L,
            resetTimeout = 5000L,
            recover = "handle"
    )
    @StartSaga
    @SagaEventHandler(associationProperty = "seatId")
    public void handle(SeatRegisteredEvent event) {
        SagaLifecycle.associateWith("airplane", event.airplaneId());
        commandGateway.sendAndWait(new AddSeatToAirplaneCommand(
                event.airplaneId()),
                2000L,
                TimeUnit.MILLISECONDS
        );
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "airplaneId")
    public void handle(SeatAddedToAirplaneEvent event) {
        SagaLifecycle.end();
    }

    @Recover
    public void onRecover(SeatRegisteredEvent event) {
        commandGateway.send(new DeregisterSeatCommand(
                event.seatId().toString()
        ));
    }
}
