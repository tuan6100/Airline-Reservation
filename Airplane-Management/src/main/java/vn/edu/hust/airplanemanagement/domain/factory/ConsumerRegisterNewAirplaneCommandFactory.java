package vn.edu.hust.airplanemanagement.domain.factory;

import vn.edu.hust.airplanemanagement.domain.factory.eventproduct.AirplaneRegisteredEventProduct;
import vn.edu.hust.airplanemanagement.domain.message.command.airplane.RegisterNewAirplaneCommand;
import vn.edu.hust.airplanemanagement.domain.message.event.airplane.AirplaneRegisteredEvent;
import vn.edu.hust.airplanemanagement.domain.model.enumeration.AirplaneState;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.Airline;
import vn.edu.hust.airplanemanagement.domain.model.valueobj.id.AirplaneId;

public class ConsumerRegisterNewAirplaneCommandFactory
implements AirplaneRegisteredEventProduct {

    private final RegisterNewAirplaneCommand command;

    public ConsumerRegisterNewAirplaneCommandFactory(RegisterNewAirplaneCommand command) {
        this.command = command;
    }

    @Override
    public AirplaneRegisteredEvent raiseNewAirplaneRegisteredEvent() {
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
