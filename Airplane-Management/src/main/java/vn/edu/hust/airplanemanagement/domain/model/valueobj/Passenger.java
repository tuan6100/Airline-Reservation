package vn.edu.hust.airplanemanagement.domain.model.valueobj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Passenger {

    private final String passengerId;
    private int age;
    private String nationId;

    public Passenger(String passengerId) {
        this.passengerId = passengerId;
    }
}
