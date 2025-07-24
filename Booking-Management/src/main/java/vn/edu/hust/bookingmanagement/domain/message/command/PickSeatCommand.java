package vn.edu.hust.bookingmanagement.domain.message.command;

import java.util.HashSet;

public record PickSeatCommand(
        Long customerId,
        HashSet<Long> seatsPicking
) {
}
