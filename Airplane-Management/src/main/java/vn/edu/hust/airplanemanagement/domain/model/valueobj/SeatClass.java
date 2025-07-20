package vn.edu.hust.airplanemanagement.domain.model.valueobj;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter(AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class SeatClass {

    private String seatClassId;
}
