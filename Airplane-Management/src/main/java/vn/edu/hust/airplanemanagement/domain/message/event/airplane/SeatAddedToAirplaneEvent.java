package vn.edu.hust.airplanemanagement.domain.message.event.airplane;

public record SeatAddedToAirplaneEvent(
        String airplaneId,
        int numberEmptySeat
) {
}
