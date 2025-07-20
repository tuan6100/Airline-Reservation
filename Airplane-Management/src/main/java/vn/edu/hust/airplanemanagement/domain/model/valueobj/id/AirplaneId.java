package vn.edu.hust.airplanemanagement.domain.model.valueobj.id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public final class AirplaneId extends EntityId {

    public AirplaneId(@NonNull UUID id) {
        super(id);
    }

    public AirplaneId(@NonNull Long id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AirplaneId other) {
            return this.id.equals(other.id);
        }
        return false;
    }
}
