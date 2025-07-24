package vn.edu.hust.bookingmanagement.domain.exception;

public class CurrentStateNotAllowedException extends RuntimeException {
    public CurrentStateNotAllowedException(String message) {
        super(message);
    }
}
