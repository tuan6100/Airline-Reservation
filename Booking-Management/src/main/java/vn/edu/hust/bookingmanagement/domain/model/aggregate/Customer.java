package vn.edu.hust.bookingmanagement.domain.model.aggregate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.*;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.hust.bookingmanagement.domain.message.command.HoldSeatCommand;
import vn.edu.hust.bookingmanagement.domain.message.command.PickSeatCommand;
import vn.edu.hust.bookingmanagement.domain.message.command.ReleaseSeatCommand;
import vn.edu.hust.bookingmanagement.domain.message.event.SeatReleasedEvent;
import vn.edu.hust.bookingmanagement.domain.message.event.SeatsPickedEvent;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("All")
public class Customer {

    @AggregateIdentifier
    @Getter(AccessLevel.PACKAGE)
    private String customerAggregateId;
    private Long customerId;
    private HashSet<Long> seatsPicked;
    LocalDateTime pickedAt;
    String failureReason;

    @Autowired
    private CommandGateway commandGateway;

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.ALWAYS)
    void handle(PickSeatCommand command) {
        // TODO: domain logic (rule)
        this.customerAggregateId = UUID.randomUUID().toString();
        AggregateLifecycle.apply(new SeatsPickedEvent(
                UUID.randomUUID().toString(),
                command.customerId(),
                command.seatsPicking()
        ));
    }

    @CommandHandler
    void handle(ReleaseSeatCommand command) {
        AggregateLifecycle.apply(new SeatReleasedEvent(
                command.seatId(),
                command.reason()
        ));
    }

    @EventSourcingHandler
    void on(SeatsPickedEvent event) {
        this.customerAggregateId = event.seatAggregateId();
        this.customerId = event.customerId();
        this.seatsPicked = event.seatsPicked();
        this.pickedAt = LocalDateTime.now();
        event.seatsPicked().parallelStream().forEach(seatId -> {
            var holdSeatCommand = new HoldSeatCommand(
                    event.seatAggregateId(),
                    seatId,
                    event.customerId()
            );
            commandGateway.send(holdSeatCommand);
        });
    }

    @EventSourcingHandler
    void on(SeatReleasedEvent event) {
        this.seatsPicked.remove(event.seatId());
        this.failureReason = event.reason();
    }
}
