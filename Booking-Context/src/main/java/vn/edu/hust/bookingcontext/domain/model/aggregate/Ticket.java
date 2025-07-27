package vn.edu.hust.bookingmanagement.domain.model.aggregate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import vn.edu.hust.bookingmanagement.domain.message.command.ticket.GenerateTicketCommand;
import vn.edu.hust.common.domain.id.TicketId;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("All")
public class Ticket {

    @AggregateIdentifier
    private TicketId id;

    @CommandHandler
    public Ticket(GenerateTicketCommand command) {

    }
}
