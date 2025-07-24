package vn.edu.hust.airplanemanagement.domain.factory.airplane;

import vn.edu.hust.airplanemanagement.domain.factory.airplane.eventproduct.AirplaneTakenOffEventProduct;
import vn.edu.hust.airplanemanagement.domain.message.command.airplane.TakeOffAirplaneCommand;
import vn.edu.hust.airplanemanagement.domain.message.event.airplane.AirplaneTakenOffEvent;

public class ConsumerTakeOffAirplaneCommandFactory
implements AirplaneTakenOffEventProduct {

    private final TakeOffAirplaneCommand command;

    public ConsumerTakeOffAirplaneCommandFactory(TakeOffAirplaneCommand command) {
        this.command = command;
    }

    @Override
    public AirplaneTakenOffEvent createNewAirplaneTakenOffEvent() {
        // TODO: Domain rules for accepting the entrypoint command
        return null;
    }
}
