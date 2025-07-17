package vn.edu.hust.airplanemanagement.domain.model.valueobj.id;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public final class SeatId extends EntityId {

    public SeatId(@NonNull UUID id) {
        super(id);
    }

    public SeatId(@NonNull Long id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SeatId other) {
            return this.id.equals(other.id);
        }
        return false;
    }
}
