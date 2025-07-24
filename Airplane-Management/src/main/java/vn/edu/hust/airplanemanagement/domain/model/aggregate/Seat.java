package vn.edu.hust.airplanemanagement.domain.model.aggregate;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateVersion;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import vn.edu.hust.airplanemanagement.domain.factory.FactoryHelper;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.DeregisterSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.RegisterNewSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.event.seat.SeatRegisteredEvent;
import vn.edu.hust.airplanemanagement.domain.model.enumeration.SeatState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.Passenger;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.SeatClass;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.SeatId;
import vn.edu.hust.airplanemanagement.domain.utility.IFieldExtractor;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

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

    @Autowired
    private transient IFieldExtractor fieldExtractor;
    private transient ConcurrentMap<String, Boolean> semanticLock;

    @PostConstruct
    public void initSemanticLock() {
        semanticLock = semanticLock = fieldExtractor.extract(this.getClass()).stream()
                .collect(Collectors.toConcurrentMap(
                        fieldName -> fieldName,
                        fieldName -> false
                ));
    }

    @CommandHandler
    public Seat(RegisterNewSeatCommand cmd) {
        var factory = FactoryHelper.getFactoryFromCommand(cmd);
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

}
