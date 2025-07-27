package vn.edu.hust.common.domain.id;

import lombok.NonNull;

import java.util.UUID;


public abstract class AggregateId {

    protected final String id;

    protected AggregateId(@NonNull String id) {
        this.id = id;
    }

    protected AggregateId(@NonNull UUID id) {
        this.id = id.toString();
    }

    protected AggregateId(@NonNull Long id) {
        this.id = id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof String) {
            return o.equals(id);
        }
        return false;
    }

    @Override
    public String toString() {
        return id;
    }
}
