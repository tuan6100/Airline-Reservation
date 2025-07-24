package vn.edu.hust.airplanemanagement.domain.message.command.seat;

import com.fasterxml.uuid.Generators;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public record RegisterNewSeatCommand(
        @TargetAggregateIdentifier
        UUID seatId,
        String seatNumber,
        String seatClassId,
        String airplaneId
) {
        public RegisterNewSeatCommand(String seatNumber, String seatClassId,
                                      String airplaneId) {
                var seatId = Generators.timeBasedEpochGenerator().generate();
                this(seatId, seatNumber, seatClassId, airplaneId);
        }
}
