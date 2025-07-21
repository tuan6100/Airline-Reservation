package vn.edu.hust.airplanemanagement.domain.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.EntityId;

@Getter
@Setter
@AllArgsConstructor
public class Pilot {

    @EntityId
    private String pilotId;
    private boolean isPresent;
    private String reasonIfAbsent;
}
