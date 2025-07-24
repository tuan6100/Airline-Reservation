package vn.edu.hust.airplanemanagement.domain.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.EntityId;

@Getter
@Setter
@AllArgsConstructor
public class Attendant {

    @EntityId
    private String attendantId;
    private boolean isPresent;
    private String reasonIfAbsent;
}
