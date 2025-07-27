package vn.edu.hust.airplanecontext.domain.model.valueobj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Airline {

    private final String airlineId;
    private int maxSeatReservingAllowed;

    public Airline(String airlineId) {
        this.airlineId = airlineId;
    }
}
