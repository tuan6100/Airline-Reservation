package vn.edu.hust.airplanemanagement.domain.model.valueobj.id;

import lombok.NonNull;

import java.util.UUID;




public final class AirplaneId extends AggregateId {

    public AirplaneId(@NonNull String id) {
        super(id);
    }

    public AirplaneId(@NonNull UUID id) {
        super(id);
    }

    public AirplaneId(@NonNull Long id) {
        super(id);
    }


}
