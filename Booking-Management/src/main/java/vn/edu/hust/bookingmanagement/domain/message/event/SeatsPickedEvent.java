package vn.edu.hust.bookingmanagement.domain.message.event;

import java.util.HashSet;

public record SeatsPickedEvent (
        String seatAggregateId,
        Long customerId,
        HashSet<Long> seatsPicked
) {
}
