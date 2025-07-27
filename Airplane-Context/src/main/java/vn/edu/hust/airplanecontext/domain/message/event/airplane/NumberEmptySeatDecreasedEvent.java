package vn.edu.hust.airplanecontext.domain.message.event.airplane;


public record NumberEmptySeatDecreasedEvent(

        String airplaneId,
        String seatId,
        int numberEmptySeat
) {
}
