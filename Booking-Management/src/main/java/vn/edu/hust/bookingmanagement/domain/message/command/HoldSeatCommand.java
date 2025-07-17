package vn.edu.hust.bookingmanagement.domain.message.command;

public record HoldSeatCommand(
        String customerAggregateId,
        Long seatId,
        Long customerId
) {
}
