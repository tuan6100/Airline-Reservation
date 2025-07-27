package vn.edu.hust.bookingmanagement.domain.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import vn.edu.hust.bookingmanagement.domain.message.event.SeatHeldEvent;

@Component
public class SeatEventListener {

    @EventHandler
    void on(SeatHeldEvent event) {
        // TODO: update new state to db
    }
}
