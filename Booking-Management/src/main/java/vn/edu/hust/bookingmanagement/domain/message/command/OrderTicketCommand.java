package vn.edu.hust.bookingmanagement.domain.message.command;

public record OrderTicketCommand (
        Long customerId,
        Long seatId
){

}
