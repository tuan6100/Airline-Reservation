package vn.edu.hust.common.domain.id;

import lombok.NonNull;

import java.util.UUID;

public final class TicketId extends AggregateId {

    TicketId(@NonNull Long id) {
        super(id);
    }

    TicketId(@NonNull String id) {
        super(id);
    }

    TicketId(@NonNull UUID id) {
        super(id);
    }
}
