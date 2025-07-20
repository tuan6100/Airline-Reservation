package vn.edu.hust.airplanemanagement.domain.model.valueobj.id;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class EntityId {

    protected String id;

    public EntityId(@NonNull UUID id) {
        this.id = id.toString();
    }

    public EntityId(@NonNull Long id) {
        this.id = id.toString();
    }

    public abstract boolean equals(Object o);
}
