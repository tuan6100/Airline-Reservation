package vn.edu.hust.airplanecontext.domain.model.valueobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SeatClass {

    private String seatClassId;
    private long price;

    public SeatClass(String seatClassId) {
        this.seatClassId = seatClassId;
    }
}
