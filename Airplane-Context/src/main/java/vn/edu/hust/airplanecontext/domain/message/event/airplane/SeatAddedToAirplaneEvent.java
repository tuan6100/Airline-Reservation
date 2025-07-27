package vn.edu.hust.airplanecontext.domain.message.event.airplane;

public record SeatAddedToAirplaneEvent(
        String airplaneId,
        int numberEmptySeat
) {
}
