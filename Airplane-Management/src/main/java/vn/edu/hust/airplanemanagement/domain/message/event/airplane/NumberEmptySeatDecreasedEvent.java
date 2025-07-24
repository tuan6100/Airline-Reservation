package vn.edu.hust.airplanemanagement.domain.message.event.airplane;

public record NumberEmptySeatDecreasedEvent(
        String airplaneId,
        String seatId,
        int numberEmptySeat
) {
}
