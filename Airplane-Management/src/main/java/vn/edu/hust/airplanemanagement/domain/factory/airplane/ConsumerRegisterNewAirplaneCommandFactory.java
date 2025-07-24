package vn.edu.hust.airplanemanagement.domain.factory.airplane;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.airplanemanagement.domain.factory.airplane.eventproduct.AirplaneRegisteredEventProduct;
import vn.edu.hust.airplanemanagement.domain.message.command.airplane.RegisterNewAirplaneCommand;
import vn.edu.hust.airplanemanagement.domain.message.event.airplane.AirplaneRegisteredEvent;
import vn.edu.hust.airplanemanagement.domain.model.enumeration.AirplaneState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.Airline;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.AirplaneId;

@Component
public class ConsumerRegisterNewAirplaneCommandFactory
implements AirplaneRegisteredEventProduct {

    @Autowired
    private QueryGateway queryGateway;

    private final RegisterNewAirplaneCommand command;

    public ConsumerRegisterNewAirplaneCommandFactory() {
        this.command = null;
    }

    public ConsumerRegisterNewAirplaneCommandFactory(RegisterNewAirplaneCommand command) {
        this.command = command;
    }

    @Override
    public AirplaneRegisteredEvent createNewAirplaneRegisteredEvent() {
        if (command == null) {
            throw new RuntimeException("Command should not be null");
        }
        // Domain rules are here
        var airplane =  new AirplaneId(command.airplaneId());
        var airline = new Airline(command.airlineId());
        return new AirplaneRegisteredEvent(
                airplane,
                command.airplaneName(),
                airline,
                AirplaneState.MAINTENANCE
        );
    }
}
