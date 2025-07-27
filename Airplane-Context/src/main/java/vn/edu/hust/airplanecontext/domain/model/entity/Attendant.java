package vn.edu.hust.airplanecontext.domain.model.entity;

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
