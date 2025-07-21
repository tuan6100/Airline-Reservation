package vn.edu.hust.airplanemanagement.domain.model.valueobj.id;

import lombok.NonNull;

import java.util.UUID;


public final class SeatId extends AggregateId {

    public SeatId(@NonNull String id) {
        super(id);
    }

    public SeatId(@NonNull UUID id) {
        super(id);
    }

    public SeatId(@NonNull Long id) {
        super(id);
    }
}
