package vn.edu.hust.bookingmanagement.domain.message.event;

public record SeatReleasedEvent(
        Long seatId,
        String reason
) {

}
