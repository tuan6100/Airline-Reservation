package vn.edu.hust.airplanemanagement.domain.factory;

import vn.edu.hust.airplanemanagement.domain.message.command.airplane.*;


public final class FactoryHelper {

    private FactoryHelper() {}

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
}
