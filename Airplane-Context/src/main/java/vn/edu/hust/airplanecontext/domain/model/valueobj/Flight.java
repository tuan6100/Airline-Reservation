package vn.edu.hust.airplanecontext.domain.model.valueobj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Flight {

    private final String flightId;

    public Flight(String flightId) {
        this.flightId = flightId;
    }
}
