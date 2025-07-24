package vn.edu.hust.airplanemanagement.domain.factory;

import vn.edu.hust.airplanemanagement.domain.factory.airplane.ConsumerAssignAirplaneToFlightCommandFactory;
import vn.edu.hust.airplanemanagement.domain.factory.airplane.ConsumerRegisterNewAirplaneCommandFactory;
import vn.edu.hust.airplanemanagement.domain.factory.airplane.ConsumerTakeOffAirplaneCommandFactory;
import vn.edu.hust.airplanemanagement.domain.factory.seat.ConsumerRegisterNewSeatCommand;
import vn.edu.hust.airplanemanagement.domain.message.command.airplane.*;
import vn.edu.hust.airplanemanagement.domain.message.command.seat.RegisterNewSeatCommand;


public final class FactoryGenerator {

    private FactoryGenerator() {}


    public static ConsumerRegisterNewAirplaneCommandFactory
    getFactoryFromCommand(RegisterNewAirplaneCommand command) {
        return new ConsumerRegisterNewAirplaneCommandFactory(command);
    }

    public static ConsumerAssignAirplaneToFlightCommandFactory
            getFactoryFromCommand(AssignAirplaneToFlightCommand command) {
        return new ConsumerAssignAirplaneToFlightCommandFactory(command);
    }

    public static ConsumerTakeOffAirplaneCommandFactory
            getFactoryFromCommand(TakeOffAirplaneCommand command) {
        return new ConsumerTakeOffAirplaneCommandFactory(command);
    }

    public static ConsumerRegisterNewSeatCommand
            getFactoryFromCommand(RegisterNewSeatCommand command) {
        return new ConsumerRegisterNewSeatCommand(command);
    }
}
