package vn.edu.hust.airplanecontext.domain.factory;

import vn.edu.hust.airplanecontext.domain.factory.airplane.ConsumerAssignAirplaneToFlightCommandFactory;
import vn.edu.hust.airplanecontext.domain.factory.airplane.ConsumerRegisterNewAirplaneCommandFactory;
import vn.edu.hust.airplanecontext.domain.factory.airplane.ConsumerTakeOffAirplaneCommandFactory;
import vn.edu.hust.airplanecontext.domain.factory.seat.ConsumerHoldSeatCommandFactory;
import vn.edu.hust.airplanecontext.domain.factory.seat.ConsumerRegisterNewSeatCommandFactory;
import vn.edu.hust.airplanecontext.domain.message.command.airplane.*;
import vn.edu.hust.airplanecontext.domain.message.command.seat.HoldSeatCommand;
import vn.edu.hust.airplanecontext.domain.message.command.seat.RegisterNewSeatCommand;


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

    public static ConsumerRegisterNewSeatCommandFactory
            getFactoryFromCommand(RegisterNewSeatCommand command) {
        return new ConsumerRegisterNewSeatCommandFactory(command);
    }

    public static ConsumerHoldSeatCommandFactory
            getFactoryFromCommand(HoldSeatCommand command) {
        return new ConsumerHoldSeatCommandFactory(command);
    }
}
